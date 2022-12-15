package com.felixweb.projeto.domain;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Cidade implements Serializable {
        static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;


    @ManyToOne()
    @JoinColumn(name = "estado_id")
    private Estado estado;

    public Cidade() {

    }

    public Cidade(Integer id, String nome, Estado estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
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
    public Estado getEstado() {
        return estado;
    }
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cidade)) return false;
        Cidade cidade = (Cidade) o;
        return Objects.equals(id, cidade.id)
                && Objects.equals(nome, cidade.nome)
                && Objects.equals(estado, cidade.estado);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, nome, estado);
    }
}
