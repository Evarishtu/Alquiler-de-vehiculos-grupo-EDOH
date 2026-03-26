package com.EDOH.AlquilaTusVehiculos.controller;

import com.EDOH.AlquilaTusVehiculos.model.Vehiculo;
import com.EDOH.AlquilaTusVehiculos.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VehiculoController {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @GetMapping("/vehiculos")
    public String listarVehiculos(Model model){
        model.addAttribute("vehiculos", vehiculoRepository.findAll());
        return "vehiculos";
    }

    @GetMapping("/vehiculos/nuevo")
    public String mostrarFormulario(Model model){
        model.addAttribute("vehiculo", new Vehiculo());
        return "crearVehiculo";
    }

    @PostMapping("/vehiculos")
    public String guardarVehiculo(@ModelAttribute Vehiculo vehiculo){
        vehiculoRepository.save(vehiculo);
        return "redirect:/vehiculos";
    }

    @GetMapping("/vehiculos/eliminar/{id}")
    public String eliminarVehiculo(@PathVariable Long id){
        vehiculoRepository.deleteById(id);
        return "redirect:/vehiculos";
    }
}