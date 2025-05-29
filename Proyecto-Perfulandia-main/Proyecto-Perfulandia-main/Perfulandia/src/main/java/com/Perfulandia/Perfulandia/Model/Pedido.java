package com.Perfulandia.Perfulandia.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private long id;

    private long idUsuario;
    private String fechapedido;
    private double total;
}
