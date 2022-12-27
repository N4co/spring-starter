package com.felixweb.projeto.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.felixweb.projeto.domain.Cliente;
import com.felixweb.projeto.dto.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.felixweb.projeto.domain.Categoria;
import com.felixweb.projeto.repositories.CategoriaRepository;

@Service
public class CategoriaService implements Serializable {

    private static final long serialVersionUID = 1l;

    @Autowired
    private CategoriaRepository repo;

    public Categoria find(Integer id) {
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Linha não encontrada Id: " + id + ", Tipo: " + Categoria.class.getName()));

    }

    public Categoria insert(Categoria obj) {
        obj.setId(null);// garantia que esta setando um obj null que ainda não existe
        return repo.save(obj);
    }

    public Categoria update(Categoria obj) {

        Categoria newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }
    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não é possível deletar uma Categoria que tenha produtos");
        }
    }

    public List<Categoria> findAll() {
        return repo.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        return repo.findAll(pageRequest);

    }

    public Categoria fromDto(CategoriaDTO objDto) {

        return new Categoria(objDto.getId(), objDto.getNome());
    }


    private void updateData(Categoria newObj, Categoria obj) {
     newObj.setNome(obj.getNome());
}
    }








