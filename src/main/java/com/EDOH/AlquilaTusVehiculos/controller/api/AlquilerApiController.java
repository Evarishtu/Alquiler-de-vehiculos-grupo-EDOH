package com.EDOH.AlquilaTusVehiculos.controller.api;

import com.EDOH.AlquilaTusVehiculos.model.Alquiler;
import com.EDOH.AlquilaTusVehiculos.repository.AlquilerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/api/alquilerVehiculos")
public class AlquilerApiController {

    private final AlquilerRepository alquilerRepository;

    public AlquilerApiController(AlquilerRepository alquilerRepository) {
        this.alquilerRepository = alquilerRepository;
    }

    @GetMapping
    public List<Alquiler> obtenerAlquileres() {
        return alquilerRepository.findAll();
    }
}