package br.com.nextgen2020.comandablue.form;

import br.com.nextgen2020.comandablue.model.entidade.Estabelecimento;
import br.com.nextgen2020.comandablue.model.entidade.CategoriaProduto;

import java.util.List;

public class CategoriasForm {
    private Estabelecimento estabelecimento;
    private List<CategoriaProduto> categorias;

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public List<CategoriaProduto> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaProduto> categorias) {
        this.categorias = categorias;
    }
}
