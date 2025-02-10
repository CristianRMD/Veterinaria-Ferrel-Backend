package com.example.Veterinaria.Ferrel_Back.Domain.Recibo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;

    @Column(name = "monto_total", nullable = false)
    private Double montoTotal;

    public Recibo(int id_cliente, double monto_total) {
        this.idCliente = id_cliente;
        this.montoTotal = monto_total;
    }
}
