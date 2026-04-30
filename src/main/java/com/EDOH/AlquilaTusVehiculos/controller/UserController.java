package com.EDOH.AlquilaTusVehiculos.controller;

import com.EDOH.AlquilaTusVehiculos.model.Alquiler;
import com.EDOH.AlquilaTusVehiculos.model.Cliente;
import com.EDOH.AlquilaTusVehiculos.model.Vehiculo;
import com.EDOH.AlquilaTusVehiculos.repository.AlquilerRepository;
import com.EDOH.AlquilaTusVehiculos.repository.ClienteRepository;
import com.EDOH.AlquilaTusVehiculos.repository.VehiculoRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Controller
public class UserController {

    private final ClienteRepository clienteRepository;
    private final VehiculoRepository vehiculoRepository;
    private final AlquilerRepository alquilerRepository;

    public UserController(ClienteRepository clienteRepository,
                          VehiculoRepository vehiculoRepository,
                          AlquilerRepository alquilerRepository) {
        this.clienteRepository = clienteRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.alquilerRepository = alquilerRepository;
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

    @GetMapping("/user/alquileres/nuevo/{vehiculoId}")
    public String nuevoAlquilerUsuario(@PathVariable Long vehiculoId,
                                       Authentication authentication,
                                       Model model) {

        Cliente cliente = obtenerClienteActual(authentication);

        if (cliente.getAlquiler() != null) {
            return "redirect:/user/alquileres";
        }

        Vehiculo vehiculo = vehiculoRepository.findById(vehiculoId)
                .orElseThrow(() -> new IllegalArgumentException("Vehículo no válido"));

        if (!vehiculo.isDisponible()) {
            return "redirect:/user/vehiculos";
        }

        model.addAttribute("cliente", cliente);
        model.addAttribute("vehiculo", vehiculo);
        model.addAttribute("alquiler", new Alquiler());

        return "user/crearAlquiler";
    }

    @PostMapping("/user/alquileres/guardar")
    public String guardarAlquilerUsuario(@ModelAttribute Alquiler alquiler,
                                         @RequestParam Long vehiculoId,
                                         Authentication authentication) {

        Cliente cliente = obtenerClienteActual(authentication);

        if (cliente.getAlquiler() != null) {
            return "redirect:/user/alquileres";
        }

        Vehiculo vehiculo = vehiculoRepository.findById(vehiculoId)
                .orElseThrow(() -> new IllegalArgumentException("Vehículo no válido"));

        if (!vehiculo.isDisponible()) {
            return "redirect:/user/vehiculos";
        }

        alquiler.setCliente(cliente);
        alquiler.setVehiculo(vehiculo);
        alquiler.setNumeroPedido("PED-" + System.currentTimeMillis());

        long dias = ChronoUnit.DAYS.between(
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

        return "redirect:/user/alquileres";
    }

    private Cliente obtenerClienteActual(Authentication authentication) {
        String username = authentication.getName();

        return clienteRepository.findByUsuarioUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario sin cliente asociado"));
    }
}