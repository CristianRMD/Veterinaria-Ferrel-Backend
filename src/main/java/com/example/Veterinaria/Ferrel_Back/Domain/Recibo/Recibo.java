package com.example.Veterinaria.Ferrel_Back.Domain.Recibo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Recibo")
@Table(name = "Recibo")
public class Recibo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recibo")
    private Long idRecibo;

    @Column(name = "id_cliente", nullable = true)
    private Long  idCliente;

    @Column(name = "monto_total", nullable = false)
    private Double montoTotal;

    @Column(name = "fecha_hora_registro", nullable = false)
    private LocalDateTime fechaHoraRegistro;


    public Recibo(long id_cliente, double monto_total) {
        this.idCliente = id_cliente;
        this.montoTotal = monto_total;
        this.fechaHoraRegistro = LocalDateTime.now();
    }
}
