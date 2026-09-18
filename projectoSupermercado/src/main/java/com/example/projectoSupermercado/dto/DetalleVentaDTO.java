package com.example.projectoSupermercado.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleVentaDTO {

    private Long id;
    private String nombrePro;
    private Integer cantidadPro;
    private Double precio;
    private Double subTotal;
}
