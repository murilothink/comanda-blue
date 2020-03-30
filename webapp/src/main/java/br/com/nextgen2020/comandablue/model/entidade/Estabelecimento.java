package br.com.nextgen2020.comandablue.model.entidade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"cnpj"}) } )
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String cnpj; // CNPJ possui 14 digitos

    @NotEmpty
    private String nome;

    private String descricao;

    @NotEmpty
    private String endereco;

    public Estabelecimento() {
        // Necessita de construtor para metodos embutidos do CategoriaProdutoRepository
    }

    public Estabelecimento(@NotEmpty String cnpj, @NotEmpty String nome, @NotEmpty String endereco, String descricao) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.endereco = endereco;
        this.descricao = descricao;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
