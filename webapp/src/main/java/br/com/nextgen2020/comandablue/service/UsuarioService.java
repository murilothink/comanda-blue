package br.com.nextgen2020.comandablue.service;


import br.com.nextgen2020.comandablue.model.entidade.Usuario;
import br.com.nextgen2020.comandablue.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Busca usuário pelo e-mail
     * @param email E-mail do usuário
     * @return Nome do usuário
     */
    public String getUsuarioNome(String email){

        Usuario usuario = usuarioRepository.findByEmail(email);

        if(usuario != null){
            return usuario.getNome();
        }

        return "";
    }

    /**
     * Verifica se a senha fornecida pelo usuario é válida.
     * Se usuário fornecer nome + e-mail + senha, criar novo usuário
     * @param email E-mail do usuário
     * @param senha Senha do usuário
     * @param nome Nome do usuário
     * @return Se senha usuário estiver OK ou usuário foi criado, retorna true
     */
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
