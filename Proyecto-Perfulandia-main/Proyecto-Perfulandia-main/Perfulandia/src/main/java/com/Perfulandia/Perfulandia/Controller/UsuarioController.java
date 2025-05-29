package com.Perfulandia.Perfulandia.Controller;
import com.Perfulandia.Perfulandia.Model.Usuario;
import com.Perfulandia.Perfulandia.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Obtener todos los usuarios
    @GetMapping("/")
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        List<Usuario> usuarios = usuarioService.obtenerTodos();
        return ResponseEntity.ok(usuarios);
    }

    // Obtener usuario por id
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable long id) {
        Usuario usuario = usuarioService.obtenerPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Obtener usuarios por nombre
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Usuario>> obtenerPorNombre(@PathVariable String nombre) {
        List<Usuario> usuarios = usuarioService.obtenerPorNombre(nombre);
        return ResponseEntity.ok(usuarios);
    }

    // Crear nuevo usuario sin necesidad de enviar id
    @PostMapping("/")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        usuario.setId(0); // Asegurarse que id sea 0 o no esté seteado para que se genere automáticamente
        Usuario creado = usuarioService.guardarUsuario(usuario);
        return ResponseEntity.ok(creado);
    }

    // Actualizar usuario por id
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable long id, @RequestBody Usuario usuario) {
        // Validar que el id en la URL y en el cuerpo coincidan
        if (usuario.getId() != id) {
            return ResponseEntity.badRequest().build();
        }
        Usuario actualizado = usuarioService.actualizarUsuario(usuario);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar usuario por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable long id) {
        Usuario usuario = usuarioService.obtenerPorId(id);
        if (usuario != null) {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
