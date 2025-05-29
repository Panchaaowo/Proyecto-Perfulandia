package com.Perfulandia.Perfulandia.Controller;


import com.Perfulandia.Perfulandia.Model.Pedido;
import com.Perfulandia.Perfulandia.Service.PedidoService;
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

    // Obtener todos los pedidos
    @GetMapping("/")
    public ResponseEntity<List<Pedido>> obtenerTodos() {
        List<Pedido> pedidos = pedidoService.listar();
        return ResponseEntity.ok(pedidos);
    }

    // Obtener pedido por id
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPorId(@PathVariable long id) {
        Pedido pedido = pedidoService.obtenerPorId(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear nuevo pedido
    @PostMapping("/")
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        pedido.setId(0); // Asegurarse que id sea 0 o no esté seteado para que se genere automáticamente
        Pedido creado = pedidoService.guardar(pedido);
        return ResponseEntity.ok(creado);
    }

    // Actualizar pedido por id
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable long id, @RequestBody Pedido pedido) {
        // Validar que el id en la URL y en el cuerpo coincidan
        if (pedido.getId() != id) {
            return ResponseEntity.badRequest().build();
        }
        Pedido actualizado = pedidoService.actualizar(id, pedido);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar pedido por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable long id) {
        Pedido pedido = pedidoService.obtenerPorId(id);
        if (pedido != null) {
            pedidoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
