package com.Perfulandia.Perfulandia.Repository;
import com.Perfulandia.Perfulandia.Model.Pedido;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PedidoRepository {
    // Arreglo que guarda todos los pedidos
    private List<Pedido> listaPedidos = new ArrayList<>();

    // Campo para generar IDs automáticos
    private long nextId = 1;

    // Método que retorna todos los pedidos
    public List<Pedido> obtenerPedidos() {
        return listaPedidos;
    }

    // Buscar pedido por su id
    public Pedido buscarPorId(long id) {
        for (Pedido pedido : listaPedidos) {
            if (pedido.getId() == id) {
                return pedido;
            }
        }
        return null;
    }

    // Guardar pedido y asignar ID automáticamente
    public Pedido guardar(Pedido pedido) {
        pedido.setId(nextId++);
        listaPedidos.add(pedido);
        return pedido;
    }

    // Actualizar pedido
    public Pedido actualizar(Pedido pedido) {
        int idPosicion = -1;
        for (int i = 0; i < listaPedidos.size(); i++) {
            if (listaPedidos.get(i).getId() == pedido.getId()) {
                idPosicion = i;
                break;
            }
        }
        if (idPosicion != -1) {
            listaPedidos.set(idPosicion, pedido);
            return pedido;
        }
        return null;
    }

    // Eliminar pedido por id
    public void eliminar(long id) {
        Pedido pedido = buscarPorId(id);
        if (pedido != null) {
            listaPedidos.remove(pedido);
        }
    }
}
