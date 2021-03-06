package br.com.nextgen2020.comandablue.form;

import br.com.nextgen2020.comandablue.model.entidade.*;

public class PedidoForm {

    private Produto produto;
    private String observacao;
    private Integer quantidade;
    private Double valorUnitario;
    private Double valorTotal;

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

    public Pedido converter(Usuario usuario, Estabelecimento estabelecimento){
        Pedido pedido = new Pedido();
        pedido.setValorTotal(this.valorTotal);
        pedido.setValorUnitario(this.valorUnitario);
        pedido.setObservacao(this.observacao);
        pedido.setQuantidade(this.quantidade);
        pedido.setClienteSolicitante(usuario);
        pedido.setProduto(this.produto);

        return pedido;
    }
}
