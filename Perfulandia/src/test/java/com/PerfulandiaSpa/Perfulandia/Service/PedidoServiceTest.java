package com.PerfulandiaSpa.Perfulandia.Service;

import com.PerfulandiaSpa.Perfulandia.Model.Pedido;
import com.PerfulandiaSpa.Perfulandia.Repository.PedidoRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    private Pedido pedido;

    @BeforeEach
    void setUp() {
        pedido = new Pedido(1L, 10L, "Pendiente", 10000.0);
    }

    @Test
    void obtenerTodos() {
        when(pedidoRepository.findAll()).thenReturn(List.of(pedido));

        List<Pedido> pedidos = pedidoService.obtenerTodos();

        assertEquals(1, pedidos.size());
        assertEquals("Pendiente", pedidos.get(0).getEstado());
    }

    @Test
    void obtenerPorId() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        Pedido resultado = pedidoService.obtenerPorId(1L);

        assertNotNull(resultado);
        assertEquals(10L, resultado.getUsuarioId());
        assertEquals(10000.0, resultado.getTotal());
    }

    @Test
    void guardarPedido() {
        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        Pedido guardado = pedidoService.guardarPedido(pedido);

        assertNotNull(guardado);
        assertEquals("Pendiente", guardado.getEstado());
        assertEquals(10000.0, guardado.getTotal());
    }

    @Test
    void actualizarPedido() {
        Pedido nuevo = new Pedido(1L, 20L, "Enviado", 15000.0);

        // Simulamos que el pedido ya existe
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        // Simulamos que al guardar, devuelve el pedido actualizado
        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Pedido actualizado = pedidoService.actualizarPedido(nuevo);

        assertNotNull(actualizado);
        assertEquals("Enviado", actualizado.getEstado());
        assertEquals(15000.0, actualizado.getTotal());
        assertEquals(20L, actualizado.getUsuarioId());
    }

    @Test
    void eliminarPedido() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        assertDoesNotThrow(() -> pedidoService.eliminarPedido(1L));

        verify(pedidoRepository, times(1)).deleteById(1L);
    }
}