package com.PerfulandiaSpa.Perfulandia.Controller;

import com.PerfulandiaSpa.Perfulandia.Model.Envio;
import com.PerfulandiaSpa.Perfulandia.Service.EnvioService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnvioControllerTest {

    @Mock
    private EnvioService envioService;

    @InjectMocks
    private EnvioController envioController;

    private Envio envio;

    @BeforeEach
    void setUp() {
        envio = new Envio(1L, "Av. Matta 123", "María González", "Entregado");
    }

    @Test
    void obtenerTodos() {
        List<Envio> lista = Arrays.asList(envio);
        when(envioService.obtenerTodos()).thenReturn(lista);

        ResponseEntity<List<Envio>> response = envioController.obtenerTodos();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("María González", response.getBody().get(0).getNombre());
    }

    @Test
    void obtenerPorId() {
        when(envioService.obtenerPorId(1L)).thenReturn(envio);

        ResponseEntity<Envio> response = envioController.obtenerPorId(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Entregado", response.getBody().getEstado());
    }

    @Test
    void crearEnvio() {
        Envio entrada = new Envio(0L, "Av. Libertad 456", "Pedro Rojas", "Pendiente");
        Envio guardado = new Envio(1L, "Av. Libertad 456", "Pedro Rojas", "Pendiente");

        when(envioService.guardarEnvio(entrada)).thenReturn(guardado);

        ResponseEntity<Envio> response = envioController.crearEnvio(entrada);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Pedro Rojas", response.getBody().getNombre());
        assertEquals("Pendiente", response.getBody().getEstado());
    }

    @Test
    void actualizarEnvio() {
        long id = 1L;
        Envio envioActualizado = new Envio(id, "Calle Sur 123", "José Pérez", "En tránsito");

        when(envioService.actualizarEnvio(id, envioActualizado)).thenReturn(envioActualizado);

        ResponseEntity<Envio> response = envioController.actualizarEnvio(id, envioActualizado);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("José Pérez", response.getBody().getNombre());
    }

    @Test
    void actualizarEnvio_idIncorrecto() {
        Envio actualizado = new Envio(99L, "Otro lugar", "Luis", "Cancelado");

        ResponseEntity<Envio> response = envioController.actualizarEnvio(1L, actualizado);

        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    void eliminarEnvio() {
        when(envioService.obtenerPorId(1L)).thenReturn(envio);
        doNothing().when(envioService).eliminarEnvio(1L);

        ResponseEntity<Void> response = envioController.eliminarEnvio(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(envioService, times(1)).eliminarEnvio(1L);
    }

    @Test
    void eliminarEnvio_noExiste() {
        when(envioService.obtenerPorId(1L)).thenReturn(null);

        ResponseEntity<Void> response = envioController.eliminarEnvio(1L);

        assertEquals(404, response.getStatusCodeValue());
    }
}