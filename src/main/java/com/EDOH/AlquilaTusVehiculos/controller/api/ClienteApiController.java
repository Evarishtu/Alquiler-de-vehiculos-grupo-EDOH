package com.EDOH.AlquilaTusVehiculos.controller.api;

import com.EDOH.AlquilaTusVehiculos.model.Cliente;
import com.EDOH.AlquilaTusVehiculos.repository.ClienteRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteApiController {

    private final ClienteRepository clienteRepository;

    public ClienteApiController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public List<Cliente> obtenerClientes() {
        return clienteRepository.findAll();
    }
}