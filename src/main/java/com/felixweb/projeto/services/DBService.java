package com.felixweb.projeto.services;

import com.felixweb.projeto.domain.*;
import com.felixweb.projeto.domain.enums.EstadoPagamento;
import com.felixweb.projeto.domain.enums.TipoCliente;
import com.felixweb.projeto.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private ItemPedidoRepository itemPedido;
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public void instantiateTestDatabase () throws ParseException {
        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Eletrônicos");
        Categoria cat3 = new Categoria(null, "Cama Mesa & Banho");
        Categoria cat4 = new Categoria(null, "Escritório");
        Categoria cat5 = new Categoria(null, "Armarinho");
        Categoria cat6 = new Categoria(null, "Tecido");
        Categoria cat7 = new Categoria(null, "Eletrodomésticos");

        Produto p1 = new Produto(null, "Computador",2500.00);
        Produto p2 = new Produto(null, "Televisão",1500.00);
        Produto p3 = new Produto(null, "Geladeira",800.00);
        Produto p4 = new Produto(null, "Notebook",1800.00);
        Produto p5 = new Produto(null, "Microondas",800.00);
        Produto p6 = new Produto(null, "Fogão",900.00);
        Produto p7 = new Produto(null, "Manta de cobrir",80.00);
        Produto p8 = new Produto(null, "Cadeiras",150.00);
        Produto p9 = new Produto(null, "Seda",25.00);
        Produto p10 = new Produto(null, "Tanquinho",900.00);
        Produto p11 = new Produto(null, "Monitor",900.00);

        cat1.getProdutos().addAll(Arrays.asList(p1,p4,p11));
        cat2.getProdutos().addAll(Arrays.asList(p2));
        cat3.getProdutos().addAll(Arrays.asList(p7));
        cat4.getProdutos().addAll(Arrays.asList(p8));
        cat5.getProdutos().addAll(Arrays.asList(p9));
        cat6.getProdutos().addAll(Arrays.asList(p9));
        cat7.getProdutos().addAll(Arrays.asList(p11,p10,p6,p3,p2));


        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat2, cat7));
        p3.getCategorias().addAll(Arrays.asList(cat7));
        p4.getCategorias().addAll(Arrays.asList(cat1));
        p5.getCategorias().addAll(Arrays.asList(cat3));
        p6.getCategorias().addAll(Arrays.asList(cat7));
        p7.getCategorias().addAll(Arrays.asList(cat3));
        p8.getCategorias().addAll(Arrays.asList(cat4));
        p9.getCategorias().addAll(Arrays.asList(cat5));
        p10.getCategorias().addAll(Arrays.asList(cat7));
        p11.getCategorias().addAll(Arrays.asList(cat7));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

        Estado e1 = new Estado(null, "São Paulo");
        Estado e2 = new Estado(null, "Minas-Gerais");
        Estado e3 = new Estado(null, "Rio-de-Janeiro");

        Cidade c1 = new Cidade(null, "Ubatuba", e1);
        Cidade c2 = new Cidade(null, "Belo-Horizonte", e2);
        Cidade c3 = new Cidade(null, "Saquarema", e3);

        e1.getCidades().addAll(Arrays.asList(c1));
        e2.getCidades().addAll(Arrays.asList(c2));
        e3.getCidades().addAll(Arrays.asList(c3));

        estadoRepository.saveAll(Arrays.asList(e1, e2, e3));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

        Cliente cli1 = new Cliente(null, "Albert Einsten", "ale@gmail.com", "383.125.489-6", TipoCliente.PESSOA_FISICA);
        cli1.getTelefones().addAll(Arrays.asList("38334556", "38334446"));

        Cliente cli2 = new Cliente(null, "Mahatma Gandhi", "judi@gmail.com", "333.444.555-6", TipoCliente.PESSOA_JURIDICA);
        cli2.getTelefones().addAll(Arrays.asList("38324558", "34587985"));

        Endereco end1 = new Endereco(c1, null, "Rua Da Tecnologia", "105", "Casa", "Bairro do Java", "Cep: 11680000-00", cli1);
        Endereco end2 = new Endereco(c2, null, "Rua Da Informação", "218", "Apartamento", "Bairro do JavaScript", "Cep: 12345678-00", cli1);

        Endereco end3 = new Endereco(c1, null, "Rua da Transformação", "217", "Apartamento", "Bairro da Vida", "Cep: 13456789.000", cli2);

        cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
        cli2.getEnderecos().addAll((Arrays.asList(end3)));

        clienteRepository.saveAll(Arrays.asList(cli1, cli2));
        enderecoRepository.saveAll(Arrays.asList(end1, end2, end3));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido(null, sdf.parse("10/12/2022 10:25"), end1, cli1);
        Pedido ped2 = new Pedido(null, sdf.parse("09/12/2022 14:25"), end2, cli1);
        Pedido ped3 = new Pedido(null, sdf.parse("08/12/2022 16:25"), end3, cli2);

        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6 );
        ped1.setPagamento(pagto1);
        Pagamento pgto2 = new PagamentoComCartao(null, EstadoPagamento.PENDENTE, ped2, 4 );
        ped2.setPagamento(pgto2);
        Pagamento pgto3 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE,
                ped3,  sdf.parse("08/12/2022 00:00"), null);
        ped3.setPagamento(pgto3);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
        cli2.getPedidos().addAll(Arrays.asList(ped3));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2, ped3));
        pagamentoRepository.saveAll(Arrays.asList(pagto1, pgto2, pgto3));

        ItemPedido ip1 = new ItemPedido(ped1, p3, 0.00, 5, 800.00);
        ItemPedido ip2 = new ItemPedido(ped2, p4, 5.00,4, 1800.00);
        ItemPedido ip3 = new ItemPedido(ped3, p1, 15.00, 3,2500.00);

        ped1.getItens().addAll(Arrays.asList((ip1)));
        ped2.getItens().addAll(Arrays.asList((ip2)));
        ped3.getItens().addAll(Arrays.asList((ip3)));

        itemPedido.saveAll(Arrays.asList(ip1,ip2, ip3));

    }
}
