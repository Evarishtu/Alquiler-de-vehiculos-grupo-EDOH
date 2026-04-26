package com.EDOH.AlquilaTusVehiculos.repository;

import com.EDOH.AlquilaTusVehiculos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);
}
