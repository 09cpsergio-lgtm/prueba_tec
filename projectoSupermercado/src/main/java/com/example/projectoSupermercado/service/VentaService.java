package com.example.projectoSupermercado.service;

import com.example.projectoSupermercado.dto.DetalleVentaDTO;
import com.example.projectoSupermercado.dto.VentaDTO;
import com.example.projectoSupermercado.exception.NotFoundException;
import com.example.projectoSupermercado.mapper.Mapper;
import com.example.projectoSupermercado.model.DetalleVenta;
import com.example.projectoSupermercado.model.Producto;
import com.example.projectoSupermercado.model.Sucursal;
import com.example.projectoSupermercado.model.Venta;
import com.example.projectoSupermercado.repository.ProductoRepository;
import com.example.projectoSupermercado.repository.SucursalRepository;
import com.example.projectoSupermercado.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaService  implements  IVentaService{

    @Autowired
   private  ProductoRepository produRepo;

    @Autowired
   private  SucursalRepository sucuRepo;

    @Autowired
   private VentaRepository ventaRepo;


    @Override
    public List<VentaDTO> traerVentas() {

        List<Venta> listV = ventaRepo.findAll();
        List<VentaDTO> ventasDto = new ArrayList<>();

        VentaDTO dto;
        for(Venta v : listV){
            dto= Mapper.vToDTO(v);
            ventasDto.add(dto);

        }

        return ventasDto;
    }

    @Override
    public VentaDTO crearVentas(VentaDTO VentaDto) {


        //validaciones
        if(VentaDto== null)throw new NotFoundException("VentaDTo es null");

        if(VentaDto.getIdSucursal()== null) throw new NotFoundException("debe indicar la id de la sucursal");

        if(VentaDto.getDetalle()==null||VentaDto.getDetalle().isEmpty()) throw new RuntimeException("debe incluir almenos un producto");

        //buscar la sucursal

        Sucursal suc = sucuRepo.findById(VentaDto.getIdSucursal()).orElse(null);

        if(suc == null) throw new NotFoundException("id de sucursal no encontrada");
        //crear  la venta

        Venta venta = new Venta();
        venta.setFecha(VentaDto.getFecha());
        venta.setEstadoV(VentaDto.getEstadoV());
        venta.setSucursal(suc);
        venta.setTotal(VentaDto.getTotal());
        //la lista de detalles

        List<DetalleVenta> detVent = new ArrayList<>();

        Double totalCalculao = 0.0;

        for(DetalleVentaDTO detDto : VentaDto.getDetalle()){

            Producto produ = produRepo.findByNombre(detDto.getNombrePro()).orElse(null);

            if(produ == null){
                throw new NotFoundException("producto no encontrado "+detDto.getNombrePro());
            }


            //crear el detalleVenta
            DetalleVenta detalleVent = new DetalleVenta();
            detalleVent.setProd(produ);
            detalleVent.setPrecioUni(detDto.getPrecio());
            detalleVent.setCantProd(detDto.getCantidadPro());
            detalleVent.setVenta(venta);



            totalCalculao= totalCalculao+(detDto.getCantidadPro()*detDto.getPrecio());


        }
        venta.setDetalle(detVent);

        venta = ventaRepo.save(venta);

        VentaDTO ventaSalida = Mapper.vToDTO(venta);

        return ventaSalida;

    }

    @Override
    public VentaDTO actualizarVentas(Long id, VentaDTO VentaDto){

        Venta v= ventaRepo.findById(id).orElse(null);
        if(v == null) throw new NotFoundException("venta no encontrada mediante su id");

        if( VentaDto.getEstadoV() != null){
            v.setEstadoV(VentaDto.getEstadoV());
        }
        if(VentaDto.getFecha()!=null){
            v.setFecha(VentaDto.getFecha());
        }
        if(VentaDto.getTotal()!=null){
            v.setTotal(VentaDto.getTotal());
        }
        if(VentaDto.getIdSucursal()!=null){
            Sucursal suc = sucuRepo.findById(VentaDto.getIdSucursal()).orElse(null);

            if(suc == null)throw new NotFoundException("sucursal no encontrada");

             v.setSucursal(suc);
        }

        ventaRepo.save(v);

        VentaDTO venDto = Mapper.vToDTO(v);

        return  venDto;

    }

    @Override
    public void eliminarVentas(Long id) {

        Venta v= ventaRepo.findById(id).orElse(null);
        if(v == null) throw new NotFoundException("venta no encontrada mediante su id");

        ventaRepo.deleteById(id);

    }
}
