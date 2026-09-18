package com.example.projectoSupermercado.controller;

import com.example.projectoSupermercado.dto.SucursalDTO;
import com.example.projectoSupermercado.model.Sucursal;
import com.example.projectoSupermercado.service.ISucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    @Autowired
    private ISucursalService sucuService;

    @GetMapping
    public ResponseEntity<List<SucursalDTO>> GetSucursales(){

        return ResponseEntity.ok(sucuService.traerSucursales());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SucursalDTO> GetSucursalById(@PathVariable Long id){

        List<SucursalDTO> sucu = sucuService.traerSucursales();

        for(SucursalDTO s : sucu){
            if(s.getId().equals(id)){
                return ResponseEntity.ok(s);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<SucursalDTO> PostSucursal(@RequestBody SucursalDTO sucu){

        SucursalDTO salida = sucuService.crearSucursal(sucu);

        return ResponseEntity.created(URI.create("/api/sucursales/"+ salida.getId())).body(salida);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalDTO> PutSucursal(@PathVariable Long id,@RequestBody SucursalDTO dto) {

        return ResponseEntity.ok(sucuService.actualizarSucursal(id,dto));


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteSucursales(@PathVariable Long id){

        sucuService.eliminarSucursal(id);
        return ResponseEntity.noContent().build();
    }
}
