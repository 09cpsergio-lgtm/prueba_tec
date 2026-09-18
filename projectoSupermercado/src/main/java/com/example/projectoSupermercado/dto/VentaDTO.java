package com.example.projectoSupermercado.dto;

import com.example.projectoSupermercado.model.DetalleVenta;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaDTO {

    private Long id;
    private LocalDate fecha;
    private String estadoV;

    private Long idSucursal;

    private List<DetalleVentaDTO> detalle;

    private Double total;
}
