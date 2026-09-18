package com.example.projectoSupermercado.controller;


import com.example.projectoSupermercado.dto.VentaDTO;

import com.example.projectoSupermercado.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private IVentaService ventaService;

    @GetMapping
    public ResponseEntity<List<VentaDTO>> GetVenta(){

        return ResponseEntity.ok(ventaService.traerVentas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> GetVentaById(@PathVariable Long id){

        List<VentaDTO> vent = ventaService.traerVentas();

        for(VentaDTO v : vent){
            if(v.getId().equals(id)){
                return ResponseEntity.ok(v);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<VentaDTO> PostVenta(@RequestBody VentaDTO vent){

        VentaDTO salida = ventaService.crearVentas(vent);

        return ResponseEntity.created(URI.create("/api/ventas/"+ salida.getId())).body(salida);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO> PutVenta(@PathVariable Long id,@RequestBody VentaDTO dto) {

        return ResponseEntity.ok(ventaService.actualizarVentas(id,dto));


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteSucursales(@PathVariable Long id){

        ventaService.eliminarVentas(id);
        return ResponseEntity.noContent().build();
    }
}
