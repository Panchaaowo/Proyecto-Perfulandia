package com.Perfulandia.Perfulandia.Repository;
import com.Perfulandia.Perfulandia.Model.Usuario;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {
    // Arreglo que guarda todos los usuarios
    private List<Usuario> listaUsuarios = new ArrayList<>();

    // Campo para generar IDs automáticos
    private long nextId = 1;

    public List<Usuario> obtenerUsuarios() {
        return listaUsuarios;
    }

    // Buscar usuario por su id
    public Usuario buscarPorId(long id) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    // Guardar usuario y asignar ID automáticamente
    public Usuario guardar(Usuario usuario) {
        usuario.setId(nextId++);
        listaUsuarios.add(usuario);
        return usuario;
    }

    // Actualizar usuario
    public Usuario actualizar(Usuario usuario) {
        int idPosicion = -1;
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getId() == usuario.getId()) {
                idPosicion = i;
                break;
            }
        }
        if (idPosicion != -1) {
            listaUsuarios.set(idPosicion, usuario);
            return usuario;
        }
        return null;
    }

    // Eliminar usuario por id
    public void eliminar(long id) {
        Usuario usuario = buscarPorId(id);
        if (usuario != null) {
            listaUsuarios.remove(usuario);
        }
    }


    public List<Usuario> buscarPorNombre(String nombre) {
        List<Usuario> usuariosEncontrados = new ArrayList<>();
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getNombre() != null && usuario.getNombre().equalsIgnoreCase(nombre)) {
                usuariosEncontrados.add(usuario);
            }
        }
        return usuariosEncontrados;
    }
}

