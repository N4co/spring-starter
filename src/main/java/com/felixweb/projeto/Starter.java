package com.felixweb.projeto;

import com.felixweb.projeto.domain.*;
import com.felixweb.projeto.domain.Cidade;
import com.felixweb.projeto.domain.enums.EstadoPagamento;
import com.felixweb.projeto.domain.enums.TipoCliente;
import com.felixweb.projeto.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class Starter implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(Starter.class, args);
	}
	@Override
	public void run(String... args) throws Exception {



	}



    }

