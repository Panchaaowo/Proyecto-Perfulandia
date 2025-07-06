package com.PerfulandiaSpa.Perfulandia.Controller;

import com.PerfulandiaSpa.Perfulandia.Model.Envio;
import com.PerfulandiaSpa.Perfulandia.Service.EnvioService;
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

    @GetMapping("/")
    public ResponseEntity<List<Envio>> obtenerTodos() {
        List<Envio> envios = envioService.obtenerTodos();
        return ResponseEntity.ok(envios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio> obtenerPorId(@PathVariable long id) {
        Envio envio = envioService.obtenerPorId(id);
        return envio != null ? ResponseEntity.ok(envio) : ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<Envio> crearEnvio(@RequestBody Envio envio) {
        Envio creado = envioService.guardarEnvio(envio);
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Envio> actualizarEnvio(@PathVariable long id, @RequestBody Envio envio) {
        if (envio.getId() != id) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Envio actualizado = envioService.actualizarEnvio(id, envio);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEnvio(@PathVariable long id) {
        if (envioService.obtenerPorId(id) != null) {
            envioService.eliminarEnvio(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}