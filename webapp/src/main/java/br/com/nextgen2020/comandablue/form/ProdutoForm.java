package br.com.nextgen2020.comandablue.form;

import br.com.nextgen2020.comandablue.model.entidade.Estabelecimento;
import br.com.nextgen2020.comandablue.model.entidade.Produto;

import java.util.EmptyStackException;

public class ProdutoForm {

    private Long id;
    private String nome;
    private Double valor;
    private String descricao;
    private String unidade;
    private CategoriaForm categoriaProduto;
    private String imagemDoProduto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CategoriaForm getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(CategoriaForm categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }

    public String getImagemDoProduto() {
        return imagemDoProduto;
    }

    public void setImagemDoProduto(String imagemDoProduto) {
        this.imagemDoProduto = imagemDoProduto;
    }

    public Produto converter(Estabelecimento estabelecimento){
        Produto produto = new Produto();
        produto.setCategoriaProduto(this.categoriaProduto.converter(estabelecimento));
        produto.setDescricao(this.descricao);
        produto.setNome(this.nome);
        produto.setUnidade(this.unidade);
        produto.setValor(this.valor);
        produto.setImagemDoProduto(this.imagemDoProduto);
        produto.setEstabelecimento(estabelecimento);
        produto.setId(this.id);

        return produto;
    }
}
