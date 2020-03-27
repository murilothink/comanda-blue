package br.com.nextgen2020.comandablue.Service;


import br.com.nextgen2020.comandablue.model.entidade.Usuario;
import br.com.nextgen2020.comandablue.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String getUsuarioNome(String email){

        Usuario usuario = usuarioRepository.findByEmail(email);

        if(usuario != null){
            return usuario.getNome();
        }

        return "";
    }

    public boolean verificaUsuarioSenha(String email, String senha, String nome){

        Usuario usuario = usuarioRepository.findByEmail(email);

        if(usuario != null){
            if(usuario.getSenha().equals(senha)){
                return true;
            }
        }else{
            if(nome.equals(null)){
                return false;
            }else{
                this.usuarioRepository.save(new Usuario(nome,email,senha));
                return true;
            }
        }
        return false;
    }

}
