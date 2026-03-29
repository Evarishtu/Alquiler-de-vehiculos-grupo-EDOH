package com.EDOH.AlquilaTusVehiculos.repository;

import com.EDOH.AlquilaTusVehiculos.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
}