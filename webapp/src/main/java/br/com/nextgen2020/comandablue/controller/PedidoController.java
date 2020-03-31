package br.com.nextgen2020.comandablue.controller;

import br.com.nextgen2020.comandablue.model.entidade.Comanda;
import br.com.nextgen2020.comandablue.model.entidade.Pedido;
import br.com.nextgen2020.comandablue.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/estabelecimento/{id-estabelecimento}/mesas/{id-mesa}/comandas/{idComanda}/pedidos")
    public ResponseEntity<List<Pedido>> listarPedidos(
            @PathVariable(value = "idEstabelecimento") Long idEstabelecimento,
            @PathVariable(value = "idMesa") Long idMesa,
            @PathVariable(value = "idComanda") Long idComanda) {

        try{
            List<Pedido> pedidosLista = pedidoService.listarPedidos(idEstabelecimento, idMesa, idComanda, null);

            if(pedidosLista != null){
                return new ResponseEntity<>(pedidosLista, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.badRequest().build();
    }

    /*
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/estabelecimento/{id-estabelecimento}/mesas/{id-mesa}/comandas/{idComanda}/pedidos?cliente={id-cliente}")
    public ResponseEntity<List<Pedido>> listarPedidos(
        @PathVariable(value = "idEstabelecimento") Long idEstabelecimento,
        @PathVariable(value = "idMesa") Long idMesa,
        @PathVariable(value = "idComanda") Long idComanda,
        @PathVariable(value = "idEmail") String emailEncriptado) {
        return ResponseEntity.ok(pedidoService.listarPedidos(idEstabelecimento, idMesa, idComanda, emailEncriptado));
    }
     */
}
