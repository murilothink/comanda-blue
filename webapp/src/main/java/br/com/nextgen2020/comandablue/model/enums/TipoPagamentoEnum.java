package br.com.nextgen2020.comandablue.model.enums;

public enum TipoPagamentoEnum {

    CREDITO("C", "Crédito"),
    DEBITO("D", "Débito"),
    DINHEIRO("$", "Dinheiro"),
    APLICATIVO("App", "Aplicativo") ;

    private String codigoTipoPagamento;

    private String descricao;


    TipoPagamentoEnum(final String codigoTipoPagemento, final String descricao){
        this.codigoTipoPagamento = codigoTipoPagamento;
        this.descricao = descricao;
    }

    public String getCodigoTipoPagamento() {
        return codigoTipoPagamento;
    }

    public String getDescricao() {
        return descricao;
    }
}
