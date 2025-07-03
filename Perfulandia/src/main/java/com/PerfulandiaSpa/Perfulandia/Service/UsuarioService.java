package com.PerfulandiaSpa.Perfulandia.Service;

import com.PerfulandiaSpa.Perfulandia.Model.Usuario;
import com.PerfulandiaSpa.Perfulandia.Repository.UsuarioRepository;
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
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(long id) {
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> obtenerPorNombre(String nombre) {
        return usuarioRepository.findByNombreIgnoreCase(nombre);
    }
}