package com.EDOH.AlquilaTusVehiculos.controller;

import com.EDOH.AlquilaTusVehiculos.model.Cliente;
import com.EDOH.AlquilaTusVehiculos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public String listarClientes(Model model){
        model.addAttribute("clientes", clienteRepository.findAll());
        return "clientes";
    }

    @GetMapping("/clientes/nuevo")
    public String mostrarFormulario(Model model){
        model.addAttribute("cliente", new Cliente());
        return "crearCliente";
    }

    @PostMapping("/clientes")
    public String guardarCliente(@ModelAttribute Cliente cliente){
        clienteRepository.save(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id){
        clienteRepository.deleteById(id);
        return "redirect:/clientes";
    }
}