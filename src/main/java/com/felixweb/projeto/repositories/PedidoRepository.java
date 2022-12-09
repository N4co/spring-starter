package com.felixweb.projeto.repositories;

import com.felixweb.projeto.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
