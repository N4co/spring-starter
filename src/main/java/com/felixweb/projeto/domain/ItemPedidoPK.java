package com.felixweb.projeto.domain;



import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable // diz que ela é subtipo de duas classes
public class ItemPedidoPK implements Serializable {
    static final long seriaVersionUID = 1l;
    @ManyToOne // um pedido tem
    @JoinColumn(name="pedido_id")
    private Pedido pedido;

    @ManyToOne // um pedido tem
    @JoinColumn(name="produto_id")
    private Produto produto;
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
