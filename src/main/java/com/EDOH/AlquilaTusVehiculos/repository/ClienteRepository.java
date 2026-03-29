package com.EDOH.AlquilaTusVehiculos.repository;

import com.EDOH.AlquilaTusVehiculos.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}