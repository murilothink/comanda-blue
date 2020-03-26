package br.com.nextgen2020.comandablue.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import br.com.nextgen2020.comandablue.model.entidade.Comanda;
@RestController
public class ComandaController {

    @PostMapping(path= "/estabelecimento/{idEstabelecimento}/mesas/{idMesa}/comandas/abrir",
                    consumes = "application/json",
                    produces = "application/json")
    public Comanda abrir(@PathVariable(value="idEstabelecimento") Long idEstabelecimento,
                    @PathVariable(value="idMesa") Long idMesa,
                    @RequestHeader(name = "COMANDA-BLUE-CLIENTE", required = true) String emailCliente){

        return null;
    }

}
