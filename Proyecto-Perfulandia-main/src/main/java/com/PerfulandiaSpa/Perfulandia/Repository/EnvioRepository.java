package com.PerfulandiaSpa.Perfulandia.Repository;

import com.PerfulandiaSpa.Perfulandia.Model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long> {
}