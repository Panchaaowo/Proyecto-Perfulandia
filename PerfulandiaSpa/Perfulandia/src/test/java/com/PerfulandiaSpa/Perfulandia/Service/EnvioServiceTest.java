package com.PerfulandiaSpa.Perfulandia.Service;

import com.PerfulandiaSpa.Perfulandia.Model.Envio;
import com.PerfulandiaSpa.Perfulandia.Repository.EnvioRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EnvioServiceTest {

    @Mock
    private EnvioRepository envioRepository;

    @InjectMocks
    private EnvioService envioService;

    private Envio envio;

    @BeforeEach
    void setUp() {
        envio = new Envio(1L, "Av. Matta 123", "María González", "Entregado");
    }

    @Test
    void obtenerTodos() {
        List<Envio> lista = Arrays.asList(envio);
        when(envioRepository.findAll()).thenReturn(lista);

        List<Envio> resultado = envioService.obtenerTodos();

        assertEquals(1, resultado.size());
        assertEquals("María González", resultado.get(0).getNombre());
    }

    @Test
    void obtenerPorId() {
        when(envioRepository.findById(1L)).thenReturn(Optional.of(envio));

        Envio resultado = envioService.obtenerPorId(1L);

        assertNotNull(resultado);
        assertEquals("Entregado", resultado.getEstado());
    }

    @Test
    void guardarEnvio() {
        when(envioRepository.save(envio)).thenReturn(envio);

        Envio resultado = envioService.guardarEnvio(envio);

        assertEquals("María González", resultado.getNombre());
        assertEquals("Entregado", resultado.getEstado());
    }

    @Test
    void actualizarEnvio() {
        Envio actualizado = new Envio(1L, "Nueva dirección", "Carlos", "Pendiente");

        when(envioRepository.existsById(1L)).thenReturn(true); // <-- Esto es lo que se usa en el service
        when(envioRepository.save(any(Envio.class))).thenReturn(actualizado);

        Envio resultado = envioService.actualizarEnvio(1L, actualizado);

        assertEquals("Carlos", resultado.getNombre());
        assertEquals("Pendiente", resultado.getEstado());
    }

    @Test
    void eliminarEnvio() {
        when(envioRepository.existsById(1L)).thenReturn(true); // <-- se usa esto, no findById
        doNothing().when(envioRepository).deleteById(1L);

        assertDoesNotThrow(() -> envioService.eliminarEnvio(1L));
        verify(envioRepository, times(1)).deleteById(1L);
    }
}