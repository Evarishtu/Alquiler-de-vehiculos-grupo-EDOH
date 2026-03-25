package com.EDOH.AlquilaTusVehiculos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 9)
    private String dni;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(length = 15)
    private String telefono;

    @OneToOne(mappedBy = "cliente")
    private Alquiler alquiler;

    public Cliente(){
    }
//    Getters
    public Long getId(){
        return id;
    }
    public String getDni(){
        return dni;
    }
    public String getNombre(){
        return nombre;
    }
    public String getApellidos(){
        return apellidos;
    }
    public String getEmail(){
        return email;
    }
    public String getTelefono(){
        return telefono;
    }
    public Alquiler getAlquiler(){
        return alquiler;
    }
//    Setters
    public void setDni(String dni){
        this.dni = dni;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setApellidos(String apellidos){
        this.apellidos = apellidos;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }
    public void setAlquiler(Alquiler alquiler){
        this.alquiler = alquiler;
    }
}
