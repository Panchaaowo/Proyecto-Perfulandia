package com.PerfulandiaSpa.Perfulandia.Repository;
import com.PerfulandiaSpa.Perfulandia.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByNombreIgnoreCase(String nombre);
}