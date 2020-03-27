package br.com.nextgen2020.comandablue.form;

import javax.validation.constraints.NotEmpty;

public class LogarUsuarioForm {

    private String email;
    private String senha;
    private String nome;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString(){
        return String.format("email=%s, senha=%s, nome=%s", this.email, this.senha, this.nome);
    }
}
