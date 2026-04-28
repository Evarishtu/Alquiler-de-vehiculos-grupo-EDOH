package com.EDOH.AlquilaTusVehiculos.controller;

import com.EDOH.AlquilaTusVehiculos.model.Alquiler;
import com.EDOH.AlquilaTusVehiculos.model.Usuario;
import com.EDOH.AlquilaTusVehiculos.repository.AlquilerRepository;
import com.EDOH.AlquilaTusVehiculos.repository.UsuarioRepository;
import com.EDOH.AlquilaTusVehiculos.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AlquilerRepository alquilerRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    // Página principal usuario
    @GetMapping("")
    public String index() {
        return "user/indexCliente";
    }

    // Ver alquileres del usuario
    @GetMapping("/alquileres")
    public String verAlquileres(Model model, Authentication authentication) {
        String username = authentication.getName();
        Usuario usuario = usuarioRepository.findByUsername(username).orElse(null);

        List<Alquiler> alquileres = alquilerRepository.findAll()
                .stream()
                .filter(a -> a.getUsuario() != null && a.getUsuario().getId().equals(usuario.getId()))
                .toList();

        model.addAttribute("alquileres", alquileres);
        return "user/alquileres";
    }

    // FORMULARIO NUEVO ALQUILER
    @GetMapping("/alquileres/nuevo")
    public String nuevoAlquiler(Model model) {
        model.addAttribute("alquiler", new Alquiler());
        model.addAttribute("vehiculos", vehiculoRepository.findAll());
        return "user/formAlquiler";
    }

    // GUARDAR ALQUILER
    @PostMapping("/alquileres/guardar")
    public String guardarAlquiler(@ModelAttribute Alquiler alquiler, Authentication authentication) {

        String username = authentication.getName();
        Usuario usuario = usuarioRepository.findByUsername(username).orElse(null);

        alquiler.setUsuario(usuario);

        alquilerRepository.save(alquiler);

        return "redirect:/user/alquileres";
    }
}