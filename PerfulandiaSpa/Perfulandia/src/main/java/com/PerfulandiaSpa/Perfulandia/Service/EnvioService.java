package com.PerfulandiaSpa.Perfulandia.Service;

import com.PerfulandiaSpa.Perfulandia.Model.Envio;
import com.PerfulandiaSpa.Perfulandia.Repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EnvioService {

    private final EnvioRepository envioRepository;

    @Autowired
    public EnvioService(EnvioRepository envioRepository) {
        this.envioRepository = envioRepository;
    }

    public List<Envio> obtenerTodos() {
        return envioRepository.findAll();
    }

    public Envio obtenerPorId(long id) {
        return envioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Envío no encontrado con ID: " + id));
    }

    public Envio guardarEnvio(Envio envio) {
        return envioRepository.save(envio);
    }

    public Envio actualizarEnvio(long id, Envio envio) {
        if (!envioRepository.existsById(id)) {
            throw new NoSuchElementException("Envío no encontrado con ID: " + id);
        }
        envio.setId(id); // Asegúrate de que el ID del envío sea el correcto
        return envioRepository.save(envio);
    }

    public void eliminarEnvio(long id) {
        if (!envioRepository.existsById(id)) {
            throw new NoSuchElementException("Envío no encontrado con ID: " + id);
        }
        envioRepository.deleteById(id);
    }
}