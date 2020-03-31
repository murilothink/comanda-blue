package br.com.nextgen2020.comandablue.controller;

import br.com.nextgen2020.comandablue.security.EncryptDecrypt;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.com.nextgen2020.comandablue.service.UsuarioService;
import br.com.nextgen2020.comandablue.form.LogarUsuarioForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
public class UsuarioController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Faz o login do usuário na aplicação
     * @param form Formulario com e-mail, senha e nome do usuário
     * @return 200 se autenticacao OK, 400 se falhou
     */
    @PostMapping(path= "/usuario/logar", consumes = "application/json", produces = "application/json")
    @Transactional
    public ResponseEntity<?> logar(@RequestBody LogarUsuarioForm form) {

        logger.info("Logar usuario recebido, " + form.toString());

        try{
            if(usuarioService.verificaUsuarioSenha(form.getEmail(), form.getSenha(),form.getNome())){
                EncryptDecrypt ed = new EncryptDecrypt();
                JSONObject json = new JSONObject();
                json.put("comandaBlueCliente", ed.encrypt(form.getEmail()));
                json.put("nome", usuarioService.getUsuarioNome(form.getEmail()));
                logger.info("Logar usuario OK, emailEnctriptado(comandaBlueCliente)=" + json.getAsString("comandaBlueCliente"));
                return ResponseEntity.ok(json);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.badRequest().build();
    }

}
