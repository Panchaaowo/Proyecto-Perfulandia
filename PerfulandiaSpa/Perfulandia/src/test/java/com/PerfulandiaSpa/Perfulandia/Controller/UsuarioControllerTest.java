package com.PerfulandiaSpa.Perfulandia.Controller;

import com.PerfulandiaSpa.Perfulandia.Model.Usuario;
import com.PerfulandiaSpa.Perfulandia.Service.UsuarioService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {
    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @Test
    void obtenerTodos() {
        List<Usuario> lista = List.of(new Usuario(1L, "Carlos", "carlos@mail.com", "123"));
        when(usuarioService.obtenerTodos()).thenReturn(lista);

        var response = usuarioController.obtenerTodos();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Carlos", response.getBody().get(0).getNombre());
    }

    @Test
    void obtenerPorId() {
        Usuario usuario = new Usuario(1L, "Ana", "ana@mail.com", "pass");
        when(usuarioService.obtenerPorId(1L)).thenReturn(usuario);

        var response = usuarioController.obtenerPorId(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Ana", response.getBody().getNombre());
    }

    @Test
    void obtenerPorNombre() {
        List<Usuario> lista = List.of(new Usuario(1L, "Luis", "luis@mail.com", "clave"));
        when(usuarioService.obtenerPorNombre("Luis")).thenReturn(lista);

        var response = usuarioController.obtenerPorNombre("Luis");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("Luis", response.getBody().get(0).getNombre());
    }

    @Test
    void crearUsuario() {
        Usuario input = new Usuario(1l, "Luis", "luis@mail.com", "abc");
        Usuario output = new Usuario(1L, "Luis", "luis@mail.com", "abc");

        when(usuarioService.guardarUsuario(input)).thenReturn(output);

        var response = usuarioController.crearUsuario(input);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Luis", response.getBody().getNombre());
    }

    @Test
    void actualizarUsuario() {
        Usuario input = new Usuario(1L, "Luis", "luis@mail.com", "nuevo");
        when(usuarioService.actualizarUsuario(input)).thenReturn(input);

        var response = usuarioController.actualizarUsuario(1L, input);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("nuevo", response.getBody().getContraseña());
    }

    @Test
    void eliminarUsuario() {
        Usuario usuario = new Usuario(1L, "Luis", "luis@mail.com", "clave");
        when(usuarioService.obtenerPorId(1L)).thenReturn(usuario);
        doNothing().when(usuarioService).eliminarUsuario(1L);

        var response = usuarioController.eliminarUsuario(1L);

        assertEquals(204, response.getStatusCodeValue());
    }
}