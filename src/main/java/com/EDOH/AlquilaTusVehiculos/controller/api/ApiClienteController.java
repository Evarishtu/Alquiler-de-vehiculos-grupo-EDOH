package com.EDOH.AlquilaTusVehiculos.controller.api;

import com.EDOH.AlquilaTusVehiculos.model.Cliente;
import com.EDOH.AlquilaTusVehiculos.repository.ClienteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ApiClienteController {
    private final ClienteRepository clienteRepository;

    public ApiClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public List<Cliente> obtenerClientes() {
        return clienteRepository.findAll();
    }
}
