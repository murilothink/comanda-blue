package br.com.nextgen2020.comandablue.form;

import javax.validation.constraints.NotEmpty;

public class LogarUsuarioForm {

    private String email;
    private String senha;

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

    @Override
    public String toString(){
        return String.format("email=%s, senha=%s", this.email, this.senha);
    }
}
