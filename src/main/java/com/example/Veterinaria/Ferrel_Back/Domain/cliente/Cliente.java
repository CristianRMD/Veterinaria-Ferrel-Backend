package com.example.Veterinaria.Ferrel_Back.Domain.cliente;


import com.example.Veterinaria.Ferrel_Back.Domain.mascota.Mascota;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private Long dni;
    private String nombre ;
    private String apellido ;
    private String telefono ;
    private String direccion ;
    private String email ;
    private Boolean activo ;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Mascota> mascotas ;

public Cliente (DatosRegistroCliente datosRegistroCliente){
    this.nombre = datosRegistroCliente.nombre();
    this.apellido =datosRegistroCliente.apellido();
    this.direccion=datosRegistroCliente.direccion();
    this.telefono=datosRegistroCliente.telefono();
    this.email=datosRegistroCliente.email();
    this.dni=datosRegistroCliente.dni();
    this.activo=true;
}

    public void desacticarCliente() {
    this.activo=false;
}

    public void actualizarDatos(DatosActualizarCliente datosActualizarCliente) {
    if (datosActualizarCliente.nombre()!=null){
        this.nombre=datosActualizarCliente.nombre();
    }
    if (datosActualizarCliente.apellido()!=null){
        this.apellido=datosActualizarCliente.apellido();
    }
    if (datosActualizarCliente.direccion()!=null){
        this.direccion=datosActualizarCliente.direccion();
    }
    if (datosActualizarCliente.email()!=null){
        this.email=datosActualizarCliente.email();
    }


    }
}
