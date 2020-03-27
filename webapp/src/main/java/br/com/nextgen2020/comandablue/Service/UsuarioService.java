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

    public boolean verificaUsuarioSenha(String email, String senha){

        Usuario usuario = usuarioRepository.findByEmail(email);

        if(usuario != null){
            if(usuario.getSenha().equals(senha)){
                return true;
            }
        }

        return false;
    }

}
