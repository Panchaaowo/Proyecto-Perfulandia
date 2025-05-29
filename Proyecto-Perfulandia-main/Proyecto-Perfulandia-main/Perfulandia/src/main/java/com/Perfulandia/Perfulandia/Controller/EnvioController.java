package com.Perfulandia.Perfulandia.Controller;
import com.Perfulandia.Perfulandia.Model.Envio;
import com.Perfulandia.Perfulandia.Service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/envios")
public class EnvioController {

    private final EnvioService envioService;

    @Autowired
    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }

    // Obtener todos los envíos
    @GetMapping("/")
    public ResponseEntity<List<Envio>> obtenerTodos() {
        List<Envio> envios = envioService.listar();
        return ResponseEntity.ok(envios);
    }

    // Obtener envío por id
    @GetMapping("/{id}")
    public ResponseEntity<Envio> obtenerPorId(@PathVariable long id) {
        Envio envio = envioService.obtenerPorId(id);
        if (envio != null) {
            return ResponseEntity.ok(envio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear nuevo envío
    @PostMapping("/")
    public ResponseEntity<Envio> crearEnvio(@RequestBody Envio envio) {
        envio.setId(0);
        Envio creado = envioService.guardar(envio);
        return ResponseEntity.ok(creado);
    }

    // Actualizar envío por id
    @PutMapping("/{id}")
    public ResponseEntity<Envio> actualizarEnvio(@PathVariable long id, @RequestBody Envio envio) {
        if (envio.getId() != id) {
            return ResponseEntity.badRequest().build();
        }
        Envio actualizado = envioService.actualizar(envio);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar envío por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEnvio(@PathVariable long id) {
        Envio envio = envioService.obtenerPorId(id);
        if (envio != null) {
            envioService.eliminar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
