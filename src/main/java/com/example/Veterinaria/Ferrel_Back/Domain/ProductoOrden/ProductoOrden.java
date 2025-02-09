package com.example.Veterinaria.Ferrel_Back.Domain.ProductoOrden;

import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.OrdenDePago;
import com.example.Veterinaria.Ferrel_Back.Domain.Producto.Producto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "ProductoOrden")
@Table(name = "ProductoOrden")
public class ProductoOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productoNombre;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_orden", nullable = false)
    @JsonBackReference
    private OrdenDePago ordenDePago;


    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    @JsonIgnore
    private Producto Producto;

    // solo para devolver el id_producto correctamente en JSON
    @JsonProperty("id_producto")
    public int getIdProducto() {
        return Producto.getId_producto();
    }

    public ProductoOrden() {}

    public ProductoOrden(Producto producto, int cantidad) {
        this.Producto = producto;
        this.productoNombre = producto.getNombre();
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecio_unitario();
        this.subtotal = this.precioUnitario * this.cantidad;
    }

}
