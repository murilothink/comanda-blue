package br.com.nextgen2020.comandablue.model.entidade;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;

import com.sun.istack.NotNull;
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public String getObservacao() {
        return observacao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    @NotNull
    private Produto produto;

    private String observacao;

    @NotEmpty
    private Integer quantidade;

    @Column(columnDefinition  = "timestamp default current_timestamp")
    @NotNull
    private Timestamp dataHora = new Timestamp(System.currentTimeMillis());

}
