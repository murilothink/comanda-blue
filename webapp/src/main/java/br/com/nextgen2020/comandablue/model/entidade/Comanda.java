package br.com.nextgen2020.comandablue.model.entidade;

import br.com.nextgen2020.comandablue.model.enums.StatusComanda;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Comanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne // varias comandas para 1 estabelecimento
    private Estabelecimento estabelecimento;

    @NotNull
    @OneToOne // 1 comanda para 1 mesa
    private Mesa mesa;

    @NotNull
    @OneToMany // varios usuarios para 1 comanda
    private List<Usuario> usuarios;

    @OneToMany // varios pedidos para 1 comanda
    private List<Pedido> itemPedido;

    @OneToMany // varios pagamentos para 1 comanda
    private List<Pagamento> pagamentos;

    @Enumerated(EnumType.STRING)
    private StatusComanda status;

    public Comanda(){

    }

    public Comanda(@NotNull Estabelecimento estabelecimento,
                   @NotNull Mesa mesa,
                   @NotNull List<Usuario> usuarios) {

        this.estabelecimento = estabelecimento;
        this.mesa = mesa;
        this.usuarios = usuarios;
        this.status = StatusComanda.ABERTO;
    }

    public Long getId() {
        return id;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Pedido> getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(List<Pedido> itemPedido) {
        this.itemPedido = itemPedido;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public StatusComanda getStatus() {
        return status;
    }

    public void setStatus(StatusComanda status) {
        this.status = status;
    }
}
