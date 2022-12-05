package com.felixweb.projeto.repositories;

import com.felixweb.projeto.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
