package com.EDOH.AlquilaTusVehiculos.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table (name = "alquileres")
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String numeroPedido;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaFin;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioTotal;

    @OneToOne
    @JoinColumn(name = "cliente_id", nullable = false, unique = true)
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "vehiculo_id", nullable = false, unique = true)
    private Vehiculo vehiculo;

    public Alquiler(){
    }
//    Getters
    public Long getId(){
        return id;
    }
    public String getNumeroPedido(){
        return numeroPedido;
    }
    public LocalDate getFechaInicio(){
        return fechaInicio;
    }
    public LocalDate getFechaFin(){
        return fechaFin;
    }
    public BigDecimal getPrecioTotal(){
        return precioTotal;
    }
    public Cliente getCliente(){
        return cliente;
    }
    public Vehiculo getVehiculo(){
        return vehiculo;
    }
//    Setters
    public void setId(Long id){
        this.id = id;
    }
    public void setNumeroPedido(String numeroPedido){
        this.numeroPedido = numeroPedido;
    }
    public void setFechaInicio(LocalDate fechaInicio){
        this.fechaInicio = fechaInicio;
    }
    public void setFechaFin(LocalDate fechaFin){
        this.fechaFin = fechaFin;
    }
    public void setPrecioTotal(BigDecimal precioTotal){
        this.precioTotal = precioTotal;
    }
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    public void setVehiculo(Vehiculo vehiculo){
        this.vehiculo = vehiculo;
    }
}
