package com.felixweb.projeto.dto;

import com.felixweb.projeto.domain.Produto;

import java.io.Serializable;

public class ProdutoDTO implements Serializable {
    private static final long seialVersionUID = 1l;

    private Integer id;
    private String nome;
    private Double preco;

    public ProdutoDTO() {

    }

    public ProdutoDTO(Produto obj) {
        id = obj.getId();
        nome = obj.getNome();
        preco = obj.getPreco();

    }

    public ProdutoDTO(Integer id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
