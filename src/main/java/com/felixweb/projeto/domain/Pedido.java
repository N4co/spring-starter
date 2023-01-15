package com.felixweb.projeto.domain;

import com.fasterxml.jackson.annotation.JsonFormat;


import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date Instante;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido") // necessario para não gerar o erro quando for
    // transiente para salvar o pedido relacionado com  o mesmo id do pagamento
    //mapemento bidirecional
    private Pagamento pagamento;


    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "endereco_de_entrega_id")
    private Endereco enderecoDeEntrega;


    @OneToMany(mappedBy="id.pedido")
    private Set<ItemPedido> itens = new HashSet<>();
    public Pedido() {

    }


    public Pedido(Integer id, Date instante, Endereco enderecoDeEntrega, Cliente cliente) {
        this.id = id;
        Instante = instante;
        this.enderecoDeEntrega = enderecoDeEntrega;
        this.cliente = cliente;
    }

    @OneToMany(mappedBy = "id.pedido")
    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
    }

    public double getValorTotal() {
        double soma = 0;
        for(ItemPedido ip : itens ) {
            soma = soma + ip.getSubTotal();
        }
        return soma;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstante() {
        return Instante;
    }

    public void setInstante(Date instante) {
        Instante = instante;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Endereco getEnderecoDeEntrega() {
        return enderecoDeEntrega;
    }

    public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido)) return false;
        Pedido pedido = (Pedido) o;
        return id.equals(pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        //formatando o que for dinheiro criando o objeto e pegando a instancia da moeda
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        // instanciando e formatando a data e hora
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        sb.append(" Pedido Número ");
        sb.append(getId());
        sb.append(" Instante do pedido ");
        sb.append(sdf.format(getInstante()));
        sb.append(", Cliente: ");
        sb.append(getCliente().getNome());
        sb.append(", Estado Pagamento: ");
        sb.append(getPagamento().getEstado().getDescricao());
        sb.append("\n Detalhes \n");
        for(ItemPedido ip : getItens()) {
            sb.append(ip.toString());
        }
        sb.append(" Valor Total: ");
        sb.append(nf.format(getValorTotal()));
        return sb.toString();
    }
}
