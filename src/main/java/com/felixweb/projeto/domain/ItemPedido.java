package com.felixweb.projeto.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ItemPedido implements Serializable {
    static final long serialVersionUID = 1l;

    @JsonIgnore
    @EmbeddedId //id embutido em um tipo auxiliar
    private ItemPedidoPK id = new ItemPedidoPK();
    private Double desconto;
    private Integer quantidade;

    private Double preco;

    public ItemPedido() {

    }
    public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
        super();
        id.setPedido(pedido);
        id.setProduto(produto);
        this.id = id;
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }


    public ItemPedidoPK getId () {
        return id;

    }
    @JsonIgnore
    public Pedido getPedido () {

        return id.getPedido();
    }

    public void setPedido( Pedido pedido) {
        id.setPedido(pedido);
    }
    public Produto getProduto () {
        return id.getProduto();

    }

    public void setProduto(Produto produto) {
        id.setProduto(produto);
    }

    public double getSubTotal () {
        return (preco - desconto) * quantidade;
    }
    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemPedido)) return false;
        ItemPedido that = (ItemPedido) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
