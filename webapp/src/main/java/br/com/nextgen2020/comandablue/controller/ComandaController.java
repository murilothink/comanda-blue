package br.com.nextgen2020.comandablue.controller;

import br.com.nextgen2020.comandablue.Service.ComandaService;
import br.com.nextgen2020.comandablue.form.PedidoForm;
import br.com.nextgen2020.comandablue.model.entidade.Pedido;
import org.hibernate.annotations.CascadeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import br.com.nextgen2020.comandablue.model.entidade.Comanda;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class ComandaController {

    @Autowired
    private ComandaService comandaService;


    private static final Logger log = LoggerFactory.getLogger(ComandaService.class);

    @PostMapping(path= "/estabelecimento/{idEstabelecimento}/mesas/{idMesa}/comandas/abrir", consumes = "application/json", produces = "application/json")
    public Comanda abrir(@PathVariable(value="idEstabelecimento") Long idEstabelecimento, @PathVariable(value="idMesa") Long idMesa, @RequestHeader(name = "COMANDA-BLUE-CLIENTE", required = true) String emailCliente){

        return comandaService.abrir(idEstabelecimento, idMesa, emailCliente);
    }

    /**
     *
     * Recebe paremetros pela URL
     * Passa os parametros para o metodo 'fazerPedido' do Classe 'ComandaService'
     *
     **/
    @PostMapping(path= "/estabelecimento/{idEstabelecimento}/mesas/{idMesa}/comandas/{idComanda}/pedir", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Comanda fazerPedido(
            @PathVariable(value="idEstabelecimento") Long idEstabelecimento,
            @PathVariable(value="idMesa") Long idMesa,
            @PathVariable(value="idComanda") Long idComanda,
            @RequestBody List<PedidoForm> itemPedido,
            @RequestHeader(name = "COMANDA-BLUE-CLIENTE", required = true) String emailCliente){

        return comandaService.fazerPedido(idComanda, emailCliente, itemPedido, idEstabelecimento, idMesa);
    }
}
