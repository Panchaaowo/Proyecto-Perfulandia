package com.Perfulandia.Perfulandia.Service;
import com.Perfulandia.Perfulandia.Model.Pedido;
import com.Perfulandia.Perfulandia.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> listar() {
        return pedidoRepository.obtenerPedidos();
    }

    public Pedido obtenerPorId(long id) {
        return pedidoRepository.buscarPorId(id);
    }

    public Pedido guardar(Pedido pedido) {
        return pedidoRepository.guardar(pedido);
    }

    public Pedido actualizar(long id, Pedido pedido) {
        return pedidoRepository.actualizar(pedido);
    }

    public void eliminar(long id) {
        pedidoRepository.eliminar(id);
    }
}
