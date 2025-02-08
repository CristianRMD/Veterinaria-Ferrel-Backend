package com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public OrdenDePago(Integer idOrdenDePago, double montoTotal) {

        this.id_orden = idOrdenDePago;
        this.total = montoTotal;
    }



}