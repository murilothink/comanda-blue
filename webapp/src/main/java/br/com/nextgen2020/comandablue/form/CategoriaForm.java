package br.com.nextgen2020.comandablue.form;

import br.com.nextgen2020.comandablue.model.entidade.CategoriaProduto;
import br.com.nextgen2020.comandablue.model.entidade.Estabelecimento;

public class CategoriaForm {

    private Long id;
    private String categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public CategoriaProduto converter(Estabelecimento estabelecimento){
        CategoriaProduto categoriaProduto = new CategoriaProduto();
        categoriaProduto.setCategoria(this.categoria);
        categoriaProduto.setId(this.id);
        categoriaProduto.setEstabelecimento(estabelecimento);

        return categoriaProduto;
    }
}
