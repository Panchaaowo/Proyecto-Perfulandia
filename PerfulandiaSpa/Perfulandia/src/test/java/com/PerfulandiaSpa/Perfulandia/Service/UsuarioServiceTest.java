package com.PerfulandiaSpa.Perfulandia.Service;

import com.PerfulandiaSpa.Perfulandia.Model.Usuario;
import com.PerfulandiaSpa.Perfulandia.Repository.UsuarioRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario(1L, "Juan Pérez", "juan@example.com", "1234");
    }

    @Test
    void obtenerTodos() {
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        List<Usuario> usuarios = usuarioService.obtenerTodos();

        assertEquals(1, usuarios.size());
        assertEquals("Juan Pérez", usuarios.get(0).getNombre());
    }

    @Test
    void obtenerPorId() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.obtenerPorId(1L);

        assertNotNull(resultado);
        assertEquals("juan@example.com", resultado.getEmail());
    }

    @Test
    void guardarUsuario() {
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario guardado = usuarioService.guardarUsuario(usuario);

        assertNotNull(guardado);
        assertEquals("Juan Pérez", guardado.getNombre());
    }

    @Test
    void actualizarUsuario() {
        Usuario nuevo = new Usuario(1L, "Ana López", "ana@example.com", "abcd");

        when(usuarioRepository.save(nuevo)).thenReturn(nuevo);

        Usuario actualizado = usuarioService.actualizarUsuario(nuevo);

        assertNotNull(actualizado);
        assertEquals("Ana López", actualizado.getNombre());
    }

    @Test
    void eliminarUsuario() {
        doNothing().when(usuarioRepository).deleteById(1L);

        assertDoesNotThrow(() -> usuarioService.eliminarUsuario(1L));
        verify(usuarioRepository, times(1)).deleteById(1L);
    }

    @Test
    void obtenerPorNombre() {
        when(usuarioRepository.findByNombreIgnoreCase("Juan Pérez")).thenReturn(List.of(usuario));

        List<Usuario> resultado = usuarioService.obtenerPorNombre("Juan Pérez");

        assertEquals(1, resultado.size());
        assertEquals("juan@example.com", resultado.get(0).getEmail());
    }
}
