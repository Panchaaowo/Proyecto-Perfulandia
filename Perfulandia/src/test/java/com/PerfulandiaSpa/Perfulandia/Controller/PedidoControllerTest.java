package com.PerfulandiaSpa.Perfulandia.Controller;

import com.PerfulandiaSpa.Perfulandia.Model.Pedido;
import com.PerfulandiaSpa.Perfulandia.Service.PedidoService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoControllerTest {

    @Mock
    private PedidoService pedidoService;

    @InjectMocks
    private PedidoController pedidoController;

    private Pedido pedido;

    @BeforeEach
    void setUp() {
        pedido = new Pedido(1L, 101L, "Entregado", 15000.0);
    }

    @Test
    void obtenerTodos() {
        List<Pedido> pedidos = Arrays.asList(pedido);
        when(pedidoService.obtenerTodos()).thenReturn(pedidos);

        ResponseEntity<List<Pedido>> response = pedidoController.obtenerTodos();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("Entregado", response.getBody().get(0).getEstado());
    }

    @Test
    void obtenerPorId() {
        when(pedidoService.obtenerPorId(1L)).thenReturn(pedido);

        ResponseEntity<Pedido> response = pedidoController.obtenerPorId(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(15000.0, response.getBody().getTotal());
    }

    @Test
    void crearPedido() {
        Pedido entrada = new Pedido(0L, 102L, "Pendiente", 20000.0);
        Pedido guardado = new Pedido(1L, 102L, "Pendiente", 20000.0);

        when(pedidoService.guardarPedido(entrada)).thenReturn(guardado);

        ResponseEntity<Pedido> response = pedidoController.crearPedido(entrada);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Pendiente", response.getBody().getEstado());
        assertEquals(20000.0, response.getBody().getTotal());
    }

    @Test
    void actualizarPedido() {
        Pedido actualizado = new Pedido(1L, 101L, "Procesando", 16000.0);
        when(pedidoService.actualizarPedido(actualizado)).thenReturn(actualizado);

        ResponseEntity<Pedido> response = pedidoController.actualizarPedido(1L, actualizado);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Procesando", response.getBody().getEstado());
    }

    @Test
    void actualizarPedido_idIncorrecto() {
        Pedido otro = new Pedido(99L, 101L, "Cancelado", 12000.0);

        ResponseEntity<Pedido> response = pedidoController.actualizarPedido(1L, otro);

        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    void eliminarPedido() {
        when(pedidoService.obtenerPorId(1L)).thenReturn(pedido);
        doNothing().when(pedidoService).eliminarPedido(1L);

        ResponseEntity<Void> response = pedidoController.eliminarPedido(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(pedidoService, times(1)).eliminarPedido(1L);
    }

    @Test
    void eliminarPedido_noExiste() {
        when(pedidoService.obtenerPorId(1L)).thenReturn(null);

        ResponseEntity<Void> response = pedidoController.eliminarPedido(1L);

        assertEquals(404, response.getStatusCodeValue());
    }
}