package br.com.nextgen2020.comandablue.model.enums;

public enum StatusComanda {

    ABERTO("A", "Comanda Aberta"),
    FECHADO("F", "Comanda Fechada");

    StatusComanda(String codigoStatusMesa, String descricao) {
        this.codigoStatusMesa = codigoStatusMesa;
        this.descricao = descricao;
    }

    private String codigoStatusMesa;

    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public String getCodigoStatusMesa() {
        return codigoStatusMesa;
    }
}
