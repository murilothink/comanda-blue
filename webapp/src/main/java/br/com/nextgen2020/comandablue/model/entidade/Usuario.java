package br.com.nextgen2020.comandablue.model.entidade;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"email"}) } )
public class Usuario {

    @NotEmpty
    private String nome;

    @Id
    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String senha;

    public Usuario(){
        // Necessita de construtor para metodos embutidos do UsuarioRepository
    }

    public Usuario(@NotEmpty String nome, @NotEmpty @Email String email, @NotEmpty String senha) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString(){
        return String.format("id: {}, email: {}, nome: {}", this.email, this.nome);
    }

}