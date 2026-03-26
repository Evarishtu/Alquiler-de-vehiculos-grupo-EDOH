package com.EDOH.AlquilaTusVehiculos.repository;

import com.EDOH.AlquilaTusVehiculos.model.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {
}