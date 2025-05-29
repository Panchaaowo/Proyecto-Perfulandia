package com.Perfulandia.Perfulandia.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Envio {
    private long id;

    private String direccion;
    private String nombre;
    private String estado;
}
