package br.com.nextgen2020.comandablue.controller;

import br.com.nextgen2020.comandablue.service.MesaService;
import br.com.nextgen2020.comandablue.form.ValidatePinForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
public class MesaController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MesaService mesaService;

    /**
     * Faz a verificacao do pin da mesa
     * @param form Formulário com pin da mesa
     * @return 200 se OK, 400 se falhou
     */
    @PostMapping(path= "/mesa/validatepin", consumes = "application/json", produces = "application/json")
    @Transactional
    public ResponseEntity<?> validatepin(@RequestBody ValidatePinForm form) {

        logger.info(form.toString());

        if(mesaService.verificaPin(form.getPin())){
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}
