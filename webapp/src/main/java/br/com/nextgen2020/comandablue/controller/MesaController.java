package br.com.nextgen2020.comandablue.controller;

import br.com.nextgen2020.comandablue.Service.MesaService;
import br.com.nextgen2020.comandablue.form.ValidatePINForm;
import br.com.nextgen2020.comandablue.model.entidade.Usuario;
import br.com.nextgen2020.comandablue.repository.UsuarioRepository;
import br.com.nextgen2020.comandablue.security.EncryptDecrypt;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.com.nextgen2020.comandablue.Service.UsuarioService;
import br.com.nextgen2020.comandablue.form.LogarUsuarioForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MesaController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MesaService mesaService;

    @PostMapping(path= "/mesa/validatepin", consumes = "application/json", produces = "application/json")
    @Transactional
    public ResponseEntity<?> validarPIN(@RequestBody ValidatePINForm form) {

        logger.info(form.toString());

        try{
            if(mesaService.verificaPIN(form.getPIN())){
                EncryptDecrypt ed = new EncryptDecrypt();
                JSONObject json = new JSONObject();
                json.put("comandaBlueCliente", ed.encrypt(form.getPIN()));
                return ResponseEntity.ok(json);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.badRequest().build();
    }

}
