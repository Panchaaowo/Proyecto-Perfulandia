package com.PerfulandiaSpa.Perfulandia.Service;

import com.PerfulandiaSpa.Perfulandia.Model.Producto;
import com.PerfulandiaSpa.Perfulandia.Repository.ProductoRepository;
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
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto(1L, "Perfume Rosa", "Aroma floral suave", 15990.0);
    }

    @Test
    void obtenerTodos() {
        when(productoRepository.findAll()).thenReturn(List.of(producto));

        List<Producto> productos = productoService.obtenerTodos();

        assertEquals(1, productos.size());
        assertEquals("Perfume Rosa", productos.get(0).getNombre());
    }

    @Test
    void obtenerPorId_existente() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Producto resultado = productoService.obtenerPorId(1L);

        assertNotNull(resultado);
        assertEquals("Aroma floral suave", resultado.getDescripcion());
    }

    @Test
    void obtenerPorId_noExiste() {
        when(productoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> productoService.obtenerPorId(1L));
    }

    @Test
    void guardarProducto() {
        when(productoRepository.save(producto)).thenReturn(producto);

        Producto guardado = productoService.guardarProducto(producto);

        assertNotNull(guardado);
        assertEquals("Perfume Rosa", guardado.getNombre());
    }

    @Test
    void actualizarProducto_existente() {
        Producto nuevo = new Producto(1L, "Perfume Azul", "Fragancia fresca", 16990.0);

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(productoRepository.save(any(Producto.class))).thenAnswer(inv -> inv.getArgument(0));

        Producto actualizado = productoService.actualizarProducto(nuevo);

        assertEquals("Perfume Azul", actualizado.getNombre());
        assertEquals("Fragancia fresca", actualizado.getDescripcion());
        assertEquals(16990.0, actualizado.getPrecio());
    }

    @Test
    void actualizarProducto_noExiste() {
        Producto nuevo = new Producto(1L, "Perfume Azul", "Fragancia fresca", 16990.0);

        when(productoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> productoService.actualizarProducto(nuevo));
    }

    @Test
    void eliminarProducto_existente() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        assertDoesNotThrow(() -> productoService.eliminarProducto(1L));
        verify(productoRepository, times(1)).deleteById(1L);
    }

    @Test
    void eliminarProducto_noExiste() {
        when(productoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> productoService.eliminarProducto(1L));
        verify(productoRepository, never()).deleteById(anyLong());
    }
}