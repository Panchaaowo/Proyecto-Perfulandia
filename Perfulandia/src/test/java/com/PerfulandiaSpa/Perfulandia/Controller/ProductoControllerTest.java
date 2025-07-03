package com.PerfulandiaSpa.Perfulandia.Controller;

import com.PerfulandiaSpa.Perfulandia.Model.Producto;
import com.PerfulandiaSpa.Perfulandia.Service.ProductoService;
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
class ProductoControllerTest {

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto(1L, "Perfume Rosado", "Aroma floral", 19990.0);
    }

    @Test
    void obtenerTodos() {
        when(productoService.obtenerTodos()).thenReturn(Arrays.asList(producto));

        ResponseEntity<List<Producto>> response = productoController.obtenerTodos();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("Perfume Rosado", response.getBody().get(0).getNombre());
    }

    @Test
    void obtenerPorId() {
        when(productoService.obtenerPorId(1L)).thenReturn(producto);

        ResponseEntity<Producto> response = productoController.obtenerPorId(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Aroma floral", response.getBody().getDescripcion());
    }

    @Test
    void crearProducto() {
        Producto nuevo = new Producto(1L, "Perfume Azul", "Aroma fresco", 14990.0);
        Producto guardado = new Producto(2L, "Perfume Azul", "Aroma fresco", 14990.0);

        when(productoService.guardarProducto(nuevo)).thenReturn(guardado);

        ResponseEntity<Producto> response = productoController.crearProducto(nuevo);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Perfume Azul", response.getBody().getNombre());
    }

    @Test
    void actualizarProducto() {
        Producto actualizado = new Producto(1L, "Perfume Rosado", "Edición limitada", 20990.0);
        when(productoService.actualizarProducto(actualizado)).thenReturn(actualizado);

        ResponseEntity<Producto> response = productoController.actualizarProducto(1L, actualizado);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Edición limitada", response.getBody().getDescripcion());
    }

    @Test
    void eliminarProducto() {
        when(productoService.obtenerPorId(1L)).thenReturn(producto);
        doNothing().when(productoService).eliminarProducto(1L);

        ResponseEntity<Void> response = productoController.eliminarProducto(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(productoService).eliminarProducto(1L);
    }

    @Test
    void actualizarProducto_idIncorrecto() {
        Producto distinto = new Producto(99L, "Error", "ID incorrecto", 0.0);
        ResponseEntity<Producto> response = productoController.actualizarProducto(1L, distinto);
        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    void eliminarProducto_noExiste() {
        when(productoService.obtenerPorId(1L)).thenReturn(null);
        ResponseEntity<Void> response = productoController.eliminarProducto(1L);
        assertEquals(404, response.getStatusCodeValue());
    }
}