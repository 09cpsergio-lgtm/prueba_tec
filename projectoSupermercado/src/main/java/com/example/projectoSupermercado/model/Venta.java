package com.example.projectoSupermercado.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private String estadoV;
    private Double total;
    @ManyToOne
    private Sucursal sucursal ;

    @OneToMany(mappedBy = "venta",cascade =CascadeType.ALL
            ,orphanRemoval = true,fetch = FetchType.EAGER)
    List<DetalleVenta> detalle = new ArrayList<>();
}
