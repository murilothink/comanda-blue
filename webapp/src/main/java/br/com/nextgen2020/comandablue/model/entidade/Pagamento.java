package br.com.nextgen2020.comandablue.model.entidade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import br.com.nextgen2020.comandablue.model.enums.TipoPagamentoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne // varios pagamentos para 1 usuario
    private Usuario cliente;

    @NotNull
    @ManyToOne // varios pagamentos para 1 comanda
    @JsonIgnore
    private Comanda comanda;

    @NotNull
    private Double valorPago;

    @Enumerated(EnumType.STRING)
    private TipoPagamentoEnum tipoPagamento;

    public Pagamento(){
        // Necessita de construtor para metodos embutidos do PagamentoRepository
    }

    public Pagamento(@NotNull Usuario cliente,
                     @NotNull Comanda comanda,
                     @NotNull Double valorPago) {
        this.cliente = cliente;
        this.comanda = comanda;
        this.valorPago = valorPago;
    }

    public Long getId() {
        return id;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public TipoPagamentoEnum getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamentoEnum tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
}
