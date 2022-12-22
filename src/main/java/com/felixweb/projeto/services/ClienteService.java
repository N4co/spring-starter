package com.felixweb.projeto.services;

import com.felixweb.projeto.domain.Cliente;
import com.felixweb.projeto.repositories.ClienteRepository;

import com.felixweb.projeto.resources.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
