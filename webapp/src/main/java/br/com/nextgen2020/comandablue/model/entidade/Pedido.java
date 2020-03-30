package br.com.nextgen2020.comandablue.model.entidade;

import java.sql.Timestamp;
import java.util.Optional;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne // varios pedidos para 1 usuario
    private Usuario clienteSolicitante;

    @NotNull
    @ManyToOne // varios pedidos para 1 produto
    private Produto produto;

    private String observacao;

    @NotNull
    private Integer quantidade;

    @NotNull
    private Double valorUnitario;

    @NotNull
    private Double valorTotal;

    @Column(columnDefinition  = "timestamp default current_timestamp")
    //@NotNull
    private Timestamp dataHora = new Timestamp(System.currentTimeMillis());

    public Long getId() {
        return id;
    }

    public Usuario getClienteSolicitante() {
        return clienteSolicitante;
    }

    public void setClienteSolicitante(Usuario clienteSolicitante) {
        this.clienteSolicitante = clienteSolicitante;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }
}
