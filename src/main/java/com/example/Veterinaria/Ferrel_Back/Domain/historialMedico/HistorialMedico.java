package com.example.Veterinaria.Ferrel_Back.Domain.historialMedico;

import com.example.Veterinaria.Ferrel_Back.Domain.mascota.Mascota;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "historialmedico")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class HistorialMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idhistorial;

    @ManyToOne
    @JoinColumn(name = "mascota_id", nullable = false) // FK en la BD
    private Mascota mascota;

    @Column(nullable = false)
    private String tipoconsulta;

    @Column(nullable = false, length = 500)
    private String motivo;

    @Column(nullable = false)
    private float temperatura;

    @Column(nullable = false, length = 1000)
    private String resumen;

    @Column(nullable = false, length = 500)
    private String receta;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date fecha;
}
