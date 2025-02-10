package com.example.Veterinaria.Ferrel_Back.Domain.ReciboOrden;

import com.example.Veterinaria.Ferrel_Back.Domain.OrdenDePago.OrdenDePago;
import com.example.Veterinaria.Ferrel_Back.Domain.Recibo.Recibo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "ReciboOrden")
@Table(name = "ReciboOrden")
public class ReciboOrden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id_recibo_orden;

    @ManyToOne
    @JoinColumn(name = "id_recibo", nullable = false)
    private Recibo recibo;

    @ManyToOne
    @JoinColumn(name = "id_orden", nullable = false)
    private OrdenDePago ordenDePago;
}
