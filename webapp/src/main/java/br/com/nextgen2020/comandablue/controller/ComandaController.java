package br.com.nextgen2020.comandablue.controller;

import br.com.nextgen2020.comandablue.security.EncryptDecrypt;
import br.com.nextgen2020.comandablue.service.ComandaService;
import br.com.nextgen2020.comandablue.model.entidade.Pedido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.nextgen2020.comandablue.model.entidade.Comanda;

import javax.transaction.Transactional;
import java.util.List;


@RestController
public class ComandaController {

    @Autowired
    private ComandaService comandaService;

    private static final Logger log = LoggerFactory.getLogger(ComandaService.class);

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(path= "/estabelecimento/mesas/{pinMesa}/comandas/abrir", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    @Transactional
    public ResponseEntity<Comanda>  abrir(@PathVariable(value="pinMesa") String pinMesa, @RequestHeader(name = "COMANDA-BLUE-CLIENTE", required = true) String emailClienteCriptografado){

        log.info("Abrir comanda recebido, pinMesa=" + pinMesa + ", emailClienteCriptografado=" + emailClienteCriptografado);

        try{
            Comanda comanda = comandaService.abrir(pinMesa, emailClienteCriptografado);
            if(comanda != null){
                log.info("Comanda aberta, id=" + comanda.getId() + ", idMesa=" + comanda.getMesa().getId() + ", idEstabelecimento=" + comanda.getEstabelecimento().getId());
                return new ResponseEntity<>(comanda, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(path= "/estabelecimento/{idEstabelecimento}/mesas/{idMesa}/comandas/{idComanda}/pedir", consumes = "application/json", produces = "application/json")
    public Comanda fazerPedido(@PathVariable(value="idEstabelecimento") Long idEstabelecimento, @PathVariable(value="idMesa") Long idMesa, @RequestBody List<Pedido> itemPedido, @PathVariable(value="idComanda") Long idComanda, @RequestHeader(name = "COMANDA-BLUE-CLIENTE", required = true) String emailCliente){
        log.info(itemPedido.toString());
        return comandaService.fazerPedido(idComanda, itemPedido, emailCliente, idEstabelecimento, idMesa);
    }
}
