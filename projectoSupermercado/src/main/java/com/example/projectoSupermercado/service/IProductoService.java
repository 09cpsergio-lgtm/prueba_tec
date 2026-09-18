package com.example.projectoSupermercado.service;

import com.example.projectoSupermercado.dto.ProductoDTO;

import java.util.List;

public interface IProductoService {

    List<ProductoDTO> traerProductos();
    ProductoDTO crearProductos(ProductoDTO productoDto);
    ProductoDTO actualizarProductos(Long id,ProductoDTO productoDto);
    void eliminarProductos(Long id);
}
