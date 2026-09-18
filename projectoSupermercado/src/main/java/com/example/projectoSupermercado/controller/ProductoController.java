package com.example.projectoSupermercado.controller;

import com.example.projectoSupermercado.dto.ProductoDTO;

import com.example.projectoSupermercado.service.IProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private IProductoService produService;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> GetProductos(){
        return  ResponseEntity.ok(produService.traerProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> GetProductoById(@PathVariable Long id){

        List<ProductoDTO> produDto = produService.traerProductos();

        for(ProductoDTO dto: produDto){

            if(dto.getId().equals(id )){
                return ResponseEntity.ok(dto);

            }

        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> PostProducto(@RequestBody ProductoDTO dto){

       ProductoDTO produ= produService.crearProductos(dto);

        return ResponseEntity.created(URI.create("/api/productos/"+ produ.getId())).body(produ);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<ProductoDTO> PutProducto(@PathVariable Long id,@RequestBody ProductoDTO dto){

        return ResponseEntity.ok(produService.actualizarProductos(id,dto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteProducto(@PathVariable Long id){

        produService.eliminarProductos(id);
        return ResponseEntity.noContent().build();
    }


}
