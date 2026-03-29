package com.EDOH.AlquilaTusVehiculos.controller;

import com.EDOH.AlquilaTusVehiculos.model.Alquiler;
import com.EDOH.AlquilaTusVehiculos.repository.AlquilerRepository;
import com.EDOH.AlquilaTusVehiculos.repository.ClienteRepository;
import com.EDOH.AlquilaTusVehiculos.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alquileres")
public class AlquilerController {

    @Autowired
    private AlquilerRepository alquilerRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @GetMapping
    public String listarAlquileres(Model model) {
        model.addAttribute("alquileres", alquilerRepository.findAll());
        return "alquileres";
    }

    @GetMapping("/nuevo")
    public String nuevoAlquiler(Model model) {
        model.addAttribute("alquiler", new Alquiler());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("vehiculos", vehiculoRepository.findAll());
        return "crearAlquiler";
    }

    @PostMapping("/guardar")
    public String guardarAlquiler(@ModelAttribute Alquiler alquiler) {
        alquilerRepository.save(alquiler);
        return "redirect:/alquileres";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAlquiler(@PathVariable Long id) {
        alquilerRepository.deleteById(id);
        return "redirect:/alquileres";
    }
}