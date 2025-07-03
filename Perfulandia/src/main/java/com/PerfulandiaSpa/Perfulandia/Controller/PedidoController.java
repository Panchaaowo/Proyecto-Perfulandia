package com.PerfulandiaSpa.Perfulandia.Controller;

import com.PerfulandiaSpa.Perfulandia.Model.Pedido;
import com.PerfulandiaSpa.Perfulandia.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Pedido>> obtenerTodos() {
        List<Pedido> pedidos = pedidoService.obtenerTodos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPorId(@PathVariable long id) {
        Pedido pedido = pedidoService.obtenerPorId(id);
        return pedido != null ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        Pedido creado = pedidoService.guardarPedido(pedido);
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable long id, @RequestBody Pedido pedido) {
        if (pedido.getId() != id) {
            return ResponseEntity.badRequest().build();
        }
        Pedido actualizado = pedidoService.actualizarPedido(pedido);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable long id) {
        if (pedidoService.obtenerPorId(id) != null) {
            pedidoService.eliminarPedido(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}