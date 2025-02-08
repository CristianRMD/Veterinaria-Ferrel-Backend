package com.example.Veterinaria.Ferrel_Back.Domain.ProductoOrden;

import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.OrdenDePago;
import com.example.Veterinaria.Ferrel_Back.Domain.Producto;
import jakarta.persistence.*;
@Entity
@Table(name = "productoOrden")
public class ProductoOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_orden")
    private OrdenDePago ordenPago;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    private String productoNombre;
    private String tipo;

    public ProductoOrden() {}

    public ProductoOrden(Producto productoClicked, int cantidad) {
        this.ordenPago = new OrdenDePago();
        this.producto = productoClicked;
        //this.cantidad = cantidad;
        //this.subtotal = cantidad * productoClicked.getPrecio_unitario();
    }



}
