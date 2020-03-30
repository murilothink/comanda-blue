package br.com.nextgen2020.comandablue.model.entidade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class CategoriaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne // varias categorias para 1 estabelecimento
    private Estabelecimento estabelecimento;

    @NotEmpty
    private String categoria;

    public CategoriaProduto() {
        // Necessita de construtor para metodos embutidos do CategoriaProdutoRepository
    }

    public CategoriaProduto(@NotNull Estabelecimento estabelecimento, @NotEmpty String categoria) {
        this.estabelecimento = estabelecimento;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
