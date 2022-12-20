package com.felixweb.projeto.services;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
                "Linha não encontrada Id: " + id + ", Tipo: " + Categoria.class.getName(), obj));
    }

    public Categoria insert(Categoria obj) {
        obj.setId(null);// garantia que esta setando um obj null que ainda não existe
        return repo.save(obj);
    }

    public Categoria update(Categoria obj) {
        find(obj.getId());
        return repo.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não é possível deletar uma Categoria que tenha produtos");
        }
    }
}







