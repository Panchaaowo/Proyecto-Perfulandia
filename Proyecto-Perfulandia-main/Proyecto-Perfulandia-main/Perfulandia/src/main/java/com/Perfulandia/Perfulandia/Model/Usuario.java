package com.Perfulandia.Perfulandia.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuario {
    private long id;

    private String email;
    private String nombre;
    private String pedido;
}
