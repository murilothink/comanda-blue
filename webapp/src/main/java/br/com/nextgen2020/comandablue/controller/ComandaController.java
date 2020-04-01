package br.com.nextgen2020.comandablue.controller;

import br.com.nextgen2020.comandablue.form.PedidoForm;
import br.com.nextgen2020.comandablue.model.entidade.Comanda;
import br.com.nextgen2020.comandablue.model.entidade.Pedido;
import br.com.nextgen2020.comandablue.service.ComandaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;


@RestController
public class ComandaController {

    @Autowired
    private ComandaService comandaService;

    private static final Logger log = LoggerFactory.getLogger(ComandaService.class);

    @PostMapping(path= "/estabelecimento/mesas/{pinMesa}/comandas/abrir",
            consumes = "application/x-www-form-urlencoded",
            produces = "application/json")
    @Transactional
    public ResponseEntity<Comanda> abrir(@PathVariable(value="pinMesa") String pinMesa,
                                         @RequestHeader(name = "COMANDA-BLUE-CLIENTE", required = true) String emailClienteCriptografado){

        log.info("Abrir comanda recebido, pinMesa=" + pinMesa + ", emailClienteCriptografado=" + emailClienteCriptografado);

        try{
            Comanda comanda = comandaService.abrir(pinMesa, emailClienteCriptografado);
            if(comanda != null){
                log.info("Comanda id=" + comanda.getId() + ", idMesa=" + comanda.getMesa().getId() + ", idEstabelecimento=" + comanda.getEstabelecimento().getId() + " retornada");
                return new ResponseEntity<>(comanda, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.badRequest().build();
    }

    /**
     *
     * Recebe paremetros pela URL
     * Passa os parametros para o metodo 'fazerPedido' do Classe 'ComandaService'
     *
     **/
    @PostMapping(path= "/estabelecimento/{idEstabelecimento}/mesas/{idMesa}/comandas/{idComanda}/pedir",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:3000")
    public Comanda fazerPedido(
            @PathVariable(value="idEstabelecimento") Long idEstabelecimento,
            @PathVariable(value="idMesa") Long idMesa,
            @PathVariable(value="idComanda") Long idComanda,
            @RequestBody List<PedidoForm> itemPedido,
            @RequestHeader(name = "COMANDA-BLUE-CLIENTE", required = true) String emailCliente) throws Exception {

        return comandaService.fazerPedido(idComanda, emailCliente, itemPedido, idEstabelecimento, idMesa);
    }

    @GetMapping("/estabelecimento/{id-estabelecimento}/mesas/{id-mesa}/comandas/{id-comanda}/pedidos")
    public ResponseEntity<List<Pedido>> listarPedidos(
            @PathVariable(value = "id-estabelecimento") Long idEstabelecimento,
            @PathVariable(value = "id-mesa") Long idMesa,
            @PathVariable(value = "id-comanda") Long idComanda,
            @RequestParam(required = false) String emailCliente){

        try{
            log.info("Email que chegou do forno: " + emailCliente);
            List<Pedido> pedidosLista = comandaService.listarPedidos(idComanda, emailCliente);

            if(pedidosLista != null){
                return new ResponseEntity<>(pedidosLista, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.badRequest().build();
    }
}
