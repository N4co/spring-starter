package com.felixweb.projeto.repositories;

import com.felixweb.projeto.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Transactional
    Cliente findByEmail(String email);
}
