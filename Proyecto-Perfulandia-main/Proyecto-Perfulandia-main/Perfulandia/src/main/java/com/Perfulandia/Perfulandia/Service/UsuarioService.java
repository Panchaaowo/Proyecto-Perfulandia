package com.Perfulandia.Perfulandia.Service;

import com.Perfulandia.Perfulandia.Model.Usuario;
import com.Perfulandia.Perfulandia.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.obtenerUsuarios();
    }

    public Usuario obtenerPorId(long id) {
        return usuarioRepository.buscarPorId(id);
    }

    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.guardar(usuario);
    }

    public Usuario actualizarUsuario(Usuario usuario) {
        return usuarioRepository.actualizar(usuario);
    }

    public void eliminarUsuario(long id) {
        usuarioRepository.eliminar(id);
    }


    public List<Usuario> obtenerPorNombre(String nombre) {
        return usuarioRepository.buscarPorNombre(nombre);
    }
}
