package com.example.Veterinaria.Ferrel_Back.Domain.Producto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity(name="Producto")
@Table(name="Producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_producto;

    private String nombre;
    private float precio_unitario;
    private int stock;


    public synchronized boolean reducirStock(int cantidad) {
        if (stock >= cantidad) {
            this.stock -= cantidad;
            return true;
        }
        return false;
    }

    public synchronized void restaurarStock(int cantidad) {
        this.stock += cantidad;
    }

    public synchronized void confirmarCompra(int cantidad) {
        if (this.stock >= cantidad) {
            this.stock -= cantidad;
        }
    }

    public synchronized void liberarStock(int cantidad) {
        this.restaurarStock(cantidad);
    }

}