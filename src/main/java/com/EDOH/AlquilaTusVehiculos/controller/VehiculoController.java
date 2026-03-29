package com.EDOH.AlquilaTusVehiculos.controller;

import com.EDOH.AlquilaTusVehiculos.model.Vehiculo;
import com.EDOH.AlquilaTusVehiculos.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    // LISTAR VEHICULOS
    @GetMapping
    public String listarVehiculos(Model model) {
        model.addAttribute("vehiculos", vehiculoRepository.findAll());
        return "vehiculos";
    }

    // FORMULARIO NUEVO VEHICULO
    @GetMapping("/nuevo")
    public String nuevoVehiculo(Model model) {
        model.addAttribute("vehiculo", new Vehiculo());
        return "crearVehiculo";
    }

    // GUARDAR VEHICULO
    @PostMapping("/guardar")
    public String guardarVehiculo(@ModelAttribute Vehiculo vehiculo) {
        vehiculoRepository.save(vehiculo);
        return "redirect:/vehiculos";
    }

    // ELIMINAR VEHICULO
    @GetMapping("/eliminar/{id}")
    public String eliminarVehiculo(@PathVariable Long id) {
        vehiculoRepository.deleteById(id);
        return "redirect:/vehiculos";
    }
}