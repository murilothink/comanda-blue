package br.com.nextgen2020.comandablue.controller;

import br.com.nextgen2020.comandablue.Service.MesaService;
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


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MesaController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MesaService mesaService;

    @PostMapping(path= "/mesa/validatepin", consumes = "application/json", produces = "application/json")
    @Transactional
    public ResponseEntity<?> validatepin(@RequestBody ValidatePinForm form) {

        logger.info(form.toString());

        try{
            if(mesaService.verificaPin(form.getPin())){
                return ResponseEntity.ok().build();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().build();
    }

}
