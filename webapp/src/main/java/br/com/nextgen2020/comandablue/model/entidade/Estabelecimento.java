package br.com.nextgen2020.comandablue.model.entidade;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"razaoSocial"}) } )
public class Estabelecimento {

    @NotEmpty
    @Id
    private String cnpj;

    @NotEmpty
    private String razaoSocial;

    private List<Funcionario> funcionarios;

    @NotEmpty
    private String endereco;

    private List<Mesa> mesa;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

//    public void setFuncionarios(List<Funcionario> funcionarios) {
//        this.funcionarios = funcionarios;
//    }

    public List<Mesa> getMesa() {
        return mesa;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
