package com.felixweb.projeto;

import com.felixweb.projeto.domain.Categoria;
import com.felixweb.projeto.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Starter implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(Starter.class, args);
	}
	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Eletrônicos");
		Categoria cat3 = new Categoria(null, "Eletrodomésticos");

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
	}
}
