package com.EDOH.AlquilaTusVehiculos.controller.api;

import com.EDOH.AlquilaTusVehiculos.model.Vehiculo;
import com.EDOH.AlquilaTusVehiculos.repository.VehiculoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoApiController {

    private final VehiculoRepository vehiculoRepository;

    public VehiculoApiController(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    @GetMapping
    public List<Vehiculo> obtenerVehiculos() {
        return vehiculoRepository.findAll();
    }
}