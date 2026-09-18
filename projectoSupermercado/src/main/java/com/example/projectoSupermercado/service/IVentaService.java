package com.example.projectoSupermercado.service;



import com.example.projectoSupermercado.dto.VentaDTO;

import java.util.List;

public interface IVentaService {

    List<VentaDTO> traerVentas();
    VentaDTO crearVentas(VentaDTO VentaDto);
    VentaDTO actualizarVentas(Long id, VentaDTO VentaDto);
    void eliminarVentas(Long id);
}
