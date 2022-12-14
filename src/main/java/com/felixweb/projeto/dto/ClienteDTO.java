package com.felixweb.projeto.dto;

import com.felixweb.projeto.domain.Cliente;
import com.felixweb.projeto.services.validator.ClienteInsert;
import com.felixweb.projeto.services.validator.ClienteUpdate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


@ClienteUpdate
public class ClienteDTO implements Serializable {
    private static final long seialVersionUID = 1l;

    private Integer id;

    @NotEmpty(message = "Preenchimento Obrigatório")
    @Length(min= 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento Obrigatório")
    @Email(message = "email inválido")
    private String email;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente obj) {
        id = obj.getId();
        nome = obj.getNome();
        email = obj.getEmail();

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
