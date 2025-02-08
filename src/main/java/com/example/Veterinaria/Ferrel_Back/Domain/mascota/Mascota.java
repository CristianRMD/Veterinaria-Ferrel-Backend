package com.example.Veterinaria.Ferrel_Back.Domain.mascota;


import com.example.Veterinaria.Ferrel_Back.Domain.cliente.Cliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "mascotas")
@Entity(name = "Mascota")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String raza;
    private int edad;
    private String sexo;
    private float peso;
    private float talla;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public void actualizarDatos(DatosActualizarMascota datos) {
        if (datos.nombre() != null) this.nombre = datos.nombre();
        if (datos.raza() != null) this.raza = datos.raza();
        if (datos.edad() != null) this.edad = datos.edad();
        if (datos.sexo() != null) this.sexo = datos.sexo();
        if (datos.peso() != null) this.peso = datos.peso();
        if (datos.talla() != null) this.talla = datos.talla();
    }
}
