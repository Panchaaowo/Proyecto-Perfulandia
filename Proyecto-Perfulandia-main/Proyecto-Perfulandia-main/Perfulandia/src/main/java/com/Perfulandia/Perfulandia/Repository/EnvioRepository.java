package com.Perfulandia.Perfulandia.Repository;
import com.Perfulandia.Perfulandia.Model.Envio;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EnvioRepository {
    private List<Envio> listaEnvios = new ArrayList<>();


    private long nextId = 1;

    public List<Envio> obtenerEnvios() {
        return listaEnvios;
    }

    public Envio buscarPorId(long id) {
        for (Envio envio : listaEnvios) {
            if (envio.getId() == id) {
                return envio;
            }
        }
        return null;
    }

    public Envio guardar(Envio envio) {
        envio.setId(nextId++);
        listaEnvios.add(envio);
        return envio;
    }

    public Envio actualizar(Envio envio) {
        int idPosicion = -1;
        for (int i = 0; i < listaEnvios.size(); i++) {
            if (listaEnvios.get(i).getId() == envio.getId()) {
                idPosicion = i;
                break;
            }
        }
        if (idPosicion != -1) {
            listaEnvios.set(idPosicion, envio);
            return envio;
        }
        return null;
    }

    // Eliminar envío por id
    public void eliminar(long id) {
        Envio envio = buscarPorId(id);
        if (envio != null) {
            listaEnvios.remove(envio);
        }
    }
}
