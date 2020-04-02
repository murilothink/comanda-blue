package br.com.nextgen2020.comandablue.controller;

import br.com.nextgen2020.comandablue.model.entidade.Pagamento;
import br.com.nextgen2020.comandablue.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    //"/comanda/{idComanda}/valor/{(double) valor}/pagar"

    @GetMapping("/comanda/{idComanda}/pagamento")
    public List<Pagamento> listarPagamento(@PathVariable(value = "idComanda", required = true) Long idComanda){

        return pagamentoService.listarPagamento(idComanda);
    }
}
