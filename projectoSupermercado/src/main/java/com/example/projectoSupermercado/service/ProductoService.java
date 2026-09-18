package com.example.projectoSupermercado.service;

import com.example.projectoSupermercado.dto.ProductoDTO;
import com.example.projectoSupermercado.exception.NotFoundException;
import com.example.projectoSupermercado.mapper.Mapper;
import com.example.projectoSupermercado.model.Producto;
import com.example.projectoSupermercado.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ProductoDTO> traerProductos() {
        return productoRepository.findAll().stream().map(Mapper::pToDto).toList();
    }

    @Override
    public ProductoDTO crearProductos(ProductoDTO productoDto) {

        Producto produc = Producto.builder()
                .nombre(productoDto.getNombre())
                .categoria(productoDto.getCategoria())
                .precio(productoDto.getPrecio())
                .cantidad(productoDto.getCantidad())
                .build();


        return Mapper.pToDto(productoRepository.save(produc));
    }

    @Override
    public ProductoDTO actualizarProductos(Long id, ProductoDTO productoDto) {

        Producto prod = productoRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("producto no encontrado"));

        prod.setNombre(productoDto.getNombre());
        prod.setCantidad(productoDto.getCantidad());
        prod.setCategoria(productoDto.getCategoria());
        prod.setPrecio(productoDto.getPrecio());

        return Mapper.pToDto(productoRepository.save(prod));

    }

    @Override
    public void eliminarProductos(Long id) {

        if(!productoRepository.existsById(id)){
            throw new NotFoundException("producto no encontrado para eliminar");
        }

        productoRepository.deleteById(id);

    }
}
