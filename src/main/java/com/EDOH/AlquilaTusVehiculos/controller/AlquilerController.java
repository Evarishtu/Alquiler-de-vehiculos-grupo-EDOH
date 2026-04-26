package com.EDOH.AlquilaTusVehiculos.controller;

import com.EDOH.AlquilaTusVehiculos.model.Alquiler;
import com.EDOH.AlquilaTusVehiculos.model.Cliente;
import com.EDOH.AlquilaTusVehiculos.model.Vehiculo;
import com.EDOH.AlquilaTusVehiculos.repository.AlquilerRepository;
import com.EDOH.AlquilaTusVehiculos.repository.ClienteRepository;
import com.EDOH.AlquilaTusVehiculos.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/admin/alquileres")
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
        return "admin/alquileres";
    }

    @GetMapping("/nuevo")
    public String nuevoAlquiler(Model model) {
        model.addAttribute("alquiler", new Alquiler());
        model.addAttribute("clientes", clienteRepository.findByAlquilerIsNull());
        model.addAttribute("vehiculos", vehiculoRepository.findAll());
        return "admin/crearAlquiler";
    }

    @PostMapping("/guardar")
    public String guardarAlquiler(@ModelAttribute Alquiler alquiler) {

        Cliente cliente = clienteRepository.findById(alquiler.getCliente().getId())
                        .orElseThrow(() -> new IllegalArgumentException("Cliente no válido"));
        Vehiculo vehiculo = vehiculoRepository.findById(alquiler.getVehiculo().getId())
                        .orElseThrow(() -> new IllegalArgumentException("Vehículo no válido"));

        alquiler.setCliente(cliente);
        alquiler.setVehiculo(vehiculo);

        // Numero pedido
        if (alquiler.getId() == null) {
            alquiler.setNumeroPedido("PED-" + System.currentTimeMillis());
        } else {
            Alquiler alquilerExistente = alquilerRepository.findById(alquiler.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Id de alquiler no válido"));
            alquiler.setNumeroPedido(alquilerExistente.getNumeroPedido());
        }
        // Calcular precio total
        long dias = java.time.temporal.ChronoUnit.DAYS.between(
                alquiler.getFechaInicio(),
                alquiler.getFechaFin()
        );
        if (dias <= 0) dias = 1;

        BigDecimal precioTotal = vehiculo.getPrecioPorDia()
                .multiply(BigDecimal.valueOf(dias));
        alquiler.setPrecioTotal(precioTotal);

        vehiculo.setDisponible(false);
        vehiculoRepository.save(vehiculo);

        alquilerRepository.save(alquiler);
        return "redirect:/admin/alquileres";
    }

    @GetMapping("/editar/{id}")
    public String editarAlquiler(@PathVariable Long id, Model model){
        Alquiler alquiler = alquilerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id de alquiler no válido: " + id));

        model.addAttribute("alquiler", alquiler);
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("vehiculos", vehiculoRepository.findAll());

        return "admin/crearAlquiler";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminarAlquiler(@PathVariable Long id) {
        Alquiler alquiler = alquilerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id de alquiler no válido: " + id));

        Vehiculo vehiculo = alquiler.getVehiculo();
        vehiculo.setDisponible(true);
        vehiculoRepository.save(vehiculo);

        alquilerRepository.delete(alquiler);

        return "redirect:/admin/alquileres";
    }
}