package com.example.projectoSupermercado.repository;

import com.example.projectoSupermercado.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository  extends JpaRepository<Venta,Long> {
}
