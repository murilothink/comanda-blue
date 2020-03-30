package br.com.nextgen2020.comandablue.model.entidade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne // varios produtos para 1 estabelecimento
    private Estabelecimento estabelecimento;

    @NotEmpty
    private String nome;

    @NotNull
    private Double valor;

    @NotEmpty
    private String descricao;

    @NotEmpty
    private String unidade;

    @NotNull
    @ManyToOne // varios produtos para 1 categoria
    private CategoriaProduto categoriaProduto;

    private String imagemDoProduto;

    public Produto() {
        // Necessita de construtor para metodos embutidos do ProdutoRepository
    }

    public Produto(@NotEmpty Estabelecimento estabelecimento,
                   @NotEmpty String nome,
                   @NotEmpty Double valor,
                   @NotEmpty String descricao,
                   @NotEmpty String unidade,
                   @NotNull CategoriaProduto categoriaProduto,
                   @NotEmpty String imagemDoProduto) {
        this.estabelecimento = estabelecimento;
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.unidade = unidade;
        this.categoriaProduto = categoriaProduto;
        this.imagemDoProduto = imagemDoProduto;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public CategoriaProduto getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }

    public String getImagemDoProduto() {
        return imagemDoProduto;
    }

    public void setImagemDoProduto(String imagemDoProduto) {
        this.imagemDoProduto = imagemDoProduto;
    }
}
