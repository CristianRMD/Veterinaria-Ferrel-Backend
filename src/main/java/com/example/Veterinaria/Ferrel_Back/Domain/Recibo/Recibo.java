package com.example.Veterinaria.Ferrel_Back.Domain.Recibo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recibo")
public class Recibo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id_recibo;
    int id_cliente;
    int id_orden;
    double monto_total;


    public Recibo(int id_cliente, int id_orden, double monto_total) {
        this.id_cliente = id_cliente;
        this.id_orden = id_orden;
        this.monto_total = monto_total;
    }

}
