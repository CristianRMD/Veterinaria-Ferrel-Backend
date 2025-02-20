package com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago;


import com.example.Veterinaria.Ferrel_Back.Domain.ProductoOrden.ProductoOrden;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "OrdenDePago")
@Table(name = "OrdenDePago")



public class OrdenDePago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrden;

    @Column(name = "total", nullable = false)
    private Double total;

    @Enumerated(EnumType.STRING)
    private EstadoOrden estado = EstadoOrden.PENDIENTE;

    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private LocalDateTime fechaExpiracion = fechaCreacion.plusMinutes(15); // 15min de reserva sino se revierte

    @OneToMany(mappedBy = "ordenDePago", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductoOrden> productos = new ArrayList<>();


    public boolean expirada() {
        return LocalDateTime.now().isAfter(fechaExpiracion);
    }
}