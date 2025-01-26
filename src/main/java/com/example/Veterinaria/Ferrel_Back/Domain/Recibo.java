package com.example.Veterinaria.Ferrel_Back.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Recibo {

    int id_recibo;
    int id_cliente;
    int id_ordenDePago;
    double monto_total;


}
