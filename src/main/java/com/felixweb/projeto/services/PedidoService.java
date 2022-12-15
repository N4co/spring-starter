package com.felixweb.projeto.services;

import com.felixweb.projeto.domain.Categoria;
import com.felixweb.projeto.domain.Pedido;
import com.felixweb.projeto.repositories.CategoriaRepository;
import com.felixweb.projeto.repositories.PedidoRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
   private PedidoRepository repo;

    public Pedido find(Integer id) {
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Linha não encontrada Id: " + id + ", Tipo: " + Pedido.class.getName(),obj));
    }
    }







