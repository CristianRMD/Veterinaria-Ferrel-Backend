package com.example.Veterinaria.Ferrel_Back.Domain.ProductoOrden;

import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.OrdenDePago;
import com.example.Veterinaria.Ferrel_Back.Domain.Producto.Producto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ProductoOrden")
public class ProductoOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productoNombre;
    private String tipo;

    // id orden
    @ManyToOne
    @JoinColumn(name = "id_orden", nullable = false)
    private OrdenDePago OrdenDePago;

    // id producto
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto Producto;


    private int cantidad;
    private double precio_total;




}
