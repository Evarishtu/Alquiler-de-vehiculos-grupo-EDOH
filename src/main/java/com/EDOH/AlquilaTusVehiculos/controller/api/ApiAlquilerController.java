package com.EDOH.AlquilaTusVehiculos.controller.api;

import com.EDOH.AlquilaTusVehiculos.model.Alquiler;
import com.EDOH.AlquilaTusVehiculos.repository.AlquilerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alquilerVehiculos")
public class ApiAlquilerController {
    private final AlquilerRepository alquilerRepository;

    public ApiAlquilerController(AlquilerRepository alquilerRepository) {
        this.alquilerRepository = alquilerRepository;
    }

    @GetMapping
    public List<Alquiler> obtenerAlquileres() {
        return alquilerRepository.findAll();
    }
}
