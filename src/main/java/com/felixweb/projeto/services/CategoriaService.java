package com.felixweb.projeto.services;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felixweb.projeto.domain.Categoria;
import com.felixweb.projeto.repositories.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
   private CategoriaRepository repo;

    public Categoria find(Integer id) {
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Linha não encontrada Id: " + id + ", Tipo: " + Categoria.class.getName(),obj));
    }
    }







