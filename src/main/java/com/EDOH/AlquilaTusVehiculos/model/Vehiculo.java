package com.EDOH.AlquilaTusVehiculos.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

@Entity
@Table(name = "vehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String matricula;

    @Column(nullable = false, length = 50)
    private String marca;

    @Column(nullable = false, length = 50)
    private String modelo;

    @Column(length = 30)
    private String color;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioPorDia;

    @Column (nullable = false)
    private boolean disponible = true;

    @JsonIgnore
    @OneToOne(mappedBy = "vehiculo")
    private Alquiler alquiler;

    public Vehiculo(){
    }
//    Getters
    public Long getId(){
        return id;
    }
    public String getMatricula(){
        return matricula;
    }
    public String getMarca(){
        return marca;
    }
    public String getModelo(){
        return modelo;
    }
    public String getColor(){
        return color;
    }
    public BigDecimal getPrecioPorDia(){
        return precioPorDia;
    }
    public boolean isDisponible(){
        return disponible;
    }
    public Alquiler getAlquiler(){
        return alquiler;
    }
//    Setters
    public void setId(Long id){
        this.id = id;
    }
    public void setMatricula(String matricula){
        this.matricula = matricula;
    }
    public void setMarca(String marca){
        this.marca = marca;
    }
    public void setModelo(String modelo){
        this.modelo = modelo;
    }
    public void setColor(String color){
        this.color = color;
    }
    public void setPrecioPorDia(BigDecimal precioPorDia){
        this.precioPorDia = precioPorDia;
    }
    public void setDisponible(boolean disponible){
        this.disponible = disponible;
    }
    public void setAlquiler(Alquiler alquiler){
        this.alquiler = alquiler;
    }
}
