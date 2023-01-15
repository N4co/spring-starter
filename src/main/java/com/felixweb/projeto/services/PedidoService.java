package com.felixweb.projeto.services;

import com.felixweb.projeto.domain.ItemPedido;
import com.felixweb.projeto.domain.PagamentoComBoleto;
import com.felixweb.projeto.domain.PagamentoComCartao;
import com.felixweb.projeto.domain.Pedido;
import com.felixweb.projeto.domain.enums.EstadoPagamento;
import com.felixweb.projeto.repositories.ItemPedidoRepository;
import com.felixweb.projeto.repositories.PagamentoRepository;
import com.felixweb.projeto.repositories.PedidoRepository;

import com.felixweb.projeto.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.*;
import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private PedidoRepository repo;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ClienteService clienteService;

    public Pedido find(Integer id) {
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Linha não encontrada Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }
    @Transactional
    public Pedido insert (Pedido obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.setCliente(clienteService.find(obj.getCliente().getId()));
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if(obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pgto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pgto, obj.getInstante());

        }
        obj = repo.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        for(ItemPedido ip : obj.getItens()) {
            ip.setDesconto(0.0);
            ip.setProduto(produtoService.find(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getPreco());
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());
        System.out.println(obj);

        return obj;
    }

    }







