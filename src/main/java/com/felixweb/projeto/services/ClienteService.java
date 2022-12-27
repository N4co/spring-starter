package com.felixweb.projeto.services;

import com.felixweb.projeto.domain.Cliente;
import com.felixweb.projeto.domain.Cliente;
import com.felixweb.projeto.dto.ClienteDTO;
import com.felixweb.projeto.repositories.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;
    public Cliente find(Integer id) {
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Linha não encontrada Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }
    public Cliente insert(Cliente obj) {
        obj.setId(null);// garantia que esta setando um obj null que ainda não existe
        return repo.save(obj);
    }

    public Cliente update(Cliente obj) {

        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }
    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Nõa é possível excluir uma categoria que contém produtos");
        }
    }

    public List<Cliente> findAll() {
        return repo.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        return repo.findAll(pageRequest);

    }

    public Cliente fromDto(ClienteDTO objDto) {
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}

