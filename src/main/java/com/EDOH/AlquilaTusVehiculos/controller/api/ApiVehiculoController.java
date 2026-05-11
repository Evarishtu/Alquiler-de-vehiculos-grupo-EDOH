package com.EDOH.AlquilaTusVehiculos.controller.api;

import com.EDOH.AlquilaTusVehiculos.model.Vehiculo;
import com.EDOH.AlquilaTusVehiculos.repository.VehiculoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class ApiVehiculoController {

    private final VehiculoRepository vehiculoRepository;

    public ApiVehiculoController(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    @GetMapping
    public List<Vehiculo> obtenerVehiculos() {
        return vehiculoRepository.findAll();
    }
}
