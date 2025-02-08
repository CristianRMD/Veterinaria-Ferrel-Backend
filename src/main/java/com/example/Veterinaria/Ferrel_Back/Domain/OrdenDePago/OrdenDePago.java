package com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago;


import com.example.Veterinaria.Ferrel_Back.Domain.ProductoOrden.ProductoOrden;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OrdenDePago")
public class OrdenDePago {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_orden;
    private double total;

    @OneToMany(mappedBy = "OrdenDePago", cascade = CascadeType.ALL)
    private List<ProductoOrden> productos;


    public OrdenDePago(Integer id_orden, double total, List<ProductoOrden> productosOrden) {
    }
}