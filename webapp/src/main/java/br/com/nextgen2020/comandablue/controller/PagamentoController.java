package br.com.nextgen2020.comandablue.controller;

import br.com.nextgen2020.comandablue.model.entidade.Pagamento;
import br.com.nextgen2020.comandablue.model.entidade.Pedido;
import br.com.nextgen2020.comandablue.service.ComandaService;
import br.com.nextgen2020.comandablue.service.PagamentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/comanda/{id-comanda}/valor/{valor}/pagar")
    @Transactional
    public ResponseEntity<Pagamento> pagar(
            @PathVariable(value = "id-comanda") Long idComanda,
            @PathVariable(value = "valor") Double valorPago,
            @RequestHeader(name = "COMANDA-BLUE-CLIENTE", required = true) String emailClienteCriptogrado) throws Exception {

        log.info("Realizar pagamento recebido, idComanda=" + idComanda + ", valorPago=" + String.valueOf(valorPago));

        try{
            Pagamento pagamento = pagamentoService.realizarPagamento(emailClienteCriptogrado, idComanda, valorPago);

            if(pagamento != null){
                log.info("Pagamento criado, id=" + pagamento.getId() + ", idComanda=" + pagamento.getComanda().getId() + ", valorPago=" + pagamento.getValorPago() +
                        ", cliente=" + pagamento.getCliente().getEmail());
                return new ResponseEntity<>(pagamento, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.badRequest().build();
    }


    @GetMapping("/comanda/{idComanda}/pagamento")
    public List<Pagamento> listarPagamento(@PathVariable(value = "idComanda", required = true) Long idComanda){

        return pagamentoService.listarPagamento(idComanda);
    }
}
