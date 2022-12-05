package com.felixweb.projeto;

import com.felixweb.projeto.domain.Categoria;
import com.felixweb.projeto.domain.Produto;
import com.felixweb.projeto.repositories.CategoriaRepository;
import com.felixweb.projeto.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Starter implements CommandLineRunner {

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
	}
}
