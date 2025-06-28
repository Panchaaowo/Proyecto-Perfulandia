package com.PerfulandiaSpa.Perfulandia.Service;

import com.PerfulandiaSpa.Perfulandia.Model.Pedido;
import com.PerfulandiaSpa.Perfulandia.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> obtenerTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido obtenerPorId(long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pedido no encontrado con id: " + id));
    }

    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido actualizarPedido(Pedido pedido) {
        Pedido existente = pedidoRepository.findById(pedido.getId())
                .orElseThrow(() -> new NoSuchElementException("Pedido no encontrado con id: " + pedido.getId()));

        existente.setUsuarioId(pedido.getUsuarioId());
        existente.setEstado(pedido.getEstado());
        existente.setTotal(pedido.getTotal());

        return pedidoRepository.save(existente);
    }

    public void eliminarPedido(long id) {
        Pedido existente = pedidoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pedido no encontrado con id: " + id));
        pedidoRepository.deleteById(id);
    }
}