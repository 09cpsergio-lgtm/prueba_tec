package com.example.projectoSupermercado.mapper;

import com.example.projectoSupermercado.dto.DetalleVentaDTO;
import com.example.projectoSupermercado.dto.ProductoDTO;
import com.example.projectoSupermercado.dto.SucursalDTO;
import com.example.projectoSupermercado.dto.VentaDTO;

import com.example.projectoSupermercado.model.Producto;
import com.example.projectoSupermercado.model.Sucursal;
import com.example.projectoSupermercado.model.Venta;


import java.util.stream.Collectors;

public class Mapper {

    //mapeo de la clase producto a productoDto
public static ProductoDTO pToDto(Producto p){

    if(p == null){
        return null;
    }
    return ProductoDTO.builder()
            .id(p.getId())
            .nombre(p.getNombre())
            .categoria(p.getCategoria())
            .precio(p.getPrecio())
            .cantidad(p.getCantidad())
            .build();
}
//mapeo de la clase sucursal al la clase sucursaldto

   public static SucursalDTO sToDTO(Sucursal s){
    if(s == null){
        return null;
    }
    return SucursalDTO.builder()
            .id(s.getId())
            .nombre(s.getNombre())
            .direccion(s.getDireccion())
            .build();
   }

   // de venta a ventaDto

    public static VentaDTO vToDTO(Venta venta){
    if(venta == null) return null;


   var detalle = venta.getDetalle().stream().map(det ->
            DetalleVentaDTO.builder()
                    .id(det.getProd().getId())
                    .nombrePro(det.getProd().getNombre())
                    .cantidadPro(det.getCantProd())
                    .precio(det.getPrecioUni())
                    .subTotal(det.getPrecioUni() * det.getCantProd())
                    .build()
            ).collect(Collectors.toList());

    var total = detalle.stream()
            .map(DetalleVentaDTO::getSubTotal)
            .reduce(0.0,Double::sum);

        return VentaDTO.builder()
                .id(venta.getId())
                .fecha(venta.getFecha())
                .idSucursal(venta.getSucursal().getId())
                .estadoV(venta.getEstadoV())
                .detalle(detalle)
                .total(total)
                .build();
    }



}
