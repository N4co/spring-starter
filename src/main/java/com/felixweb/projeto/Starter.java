package com.felixweb.projeto;

import com.felixweb.projeto.domain.*;
import com.felixweb.projeto.domain.Cidade;
import com.felixweb.projeto.domain.enums.TipoCliente;
import com.felixweb.projeto.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Starter implements CommandLineRunner {

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

	public static void main(String[] args) {
		SpringApplication.run(Starter.class, args);
	}
	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Eletrônicos");
		Categoria cat3 = new Categoria(null, "Eletrodomésticos");

		Produto p1 = new Produto(null, "Computador",2500.00);
		Produto p2 = new Produto(null, "Televisão",1500.00);
		Produto p3 = new Produto(null, "Geladeira",800.00);
		Produto p4 = new Produto(null, "Notebook",1800.00);
		Produto p5 = new Produto(null, "Microondas",800.00);
		Produto p6 = new Produto(null, "Fogão",900.00);

		cat1.getProdutos().addAll(Arrays.asList(p1,p4));
		cat2.getProdutos().addAll(Arrays.asList((p2)));
		cat3.getProdutos().addAll(Arrays.asList(p3,p5,p6));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat2));
		p3.getCategorias().addAll(Arrays.asList(cat3));
		p4.getCategorias().addAll(Arrays.asList(cat1));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));

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

    }
}
