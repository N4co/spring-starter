package com.felixweb.projeto.repositories;

import com.felixweb.projeto.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
