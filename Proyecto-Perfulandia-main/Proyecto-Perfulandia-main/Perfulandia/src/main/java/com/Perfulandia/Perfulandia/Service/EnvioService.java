package com.Perfulandia.Perfulandia.Service;
import com.Perfulandia.Perfulandia.Model.Envio;
import com.Perfulandia.Perfulandia.Repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvioService {

    private final EnvioRepository envioRepository;

    @Autowired
    public EnvioService(EnvioRepository envioRepository) {
        this.envioRepository = envioRepository;
    }

    public List<Envio> listar() {
        return envioRepository.obtenerEnvios();
    }

    public Envio obtenerPorId(long id) {
        return envioRepository.buscarPorId(id);
    }

    public Envio guardar(Envio envio) {
        return envioRepository.guardar(envio);
    }

    public Envio actualizar(Envio envio) {
        return envioRepository.actualizar(envio);
    }

    public void eliminar(long id) {
        envioRepository.eliminar(id);
    }
}
