package com.EDOH.AlquilaTusVehiculos.controller;

import com.EDOH.AlquilaTusVehiculos.model.Cliente;
import com.EDOH.AlquilaTusVehiculos.repository.ClienteRepository;
import com.EDOH.AlquilaTusVehiculos.repository.VehiculoRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private final ClienteRepository clienteRepository;
    private final VehiculoRepository vehiculoRepository;

    public UserController(ClienteRepository clienteRepository,
                          VehiculoRepository vehiculoRepository) {
        this.clienteRepository = clienteRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    @GetMapping("/user")
    public String userHome(Authentication authentication, Model model) {
        Cliente cliente = obtenerClienteActual(authentication);
        model.addAttribute("cliente", cliente);
        return "user/indexCliente";
    }

    @GetMapping("/user/vehiculos")
    public String verVehiculosDisponibles(Model model) {
        model.addAttribute("vehiculos", vehiculoRepository.findByDisponibleTrue());
        return "user/vehiculos";
    }

    @GetMapping("/user/alquileres")
    public String verMisAlquileres(Authentication authentication, Model model) {
        Cliente cliente = obtenerClienteActual(authentication);
        model.addAttribute("cliente", cliente);
        model.addAttribute("alquiler", cliente.getAlquiler());
        return "user/alquileres";
    }

    private Cliente obtenerClienteActual(Authentication authentication) {
        String username = authentication.getName();

        return clienteRepository.findByUsuarioUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado para el usuario: " + username));
    }
}