package com.PerfulandiaSpa.Perfulandia.Repository;

import com.PerfulandiaSpa.Perfulandia.Model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}