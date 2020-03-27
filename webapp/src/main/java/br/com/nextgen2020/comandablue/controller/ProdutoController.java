package br.com.nextgen2020.comandablue.controller;

import br.com.nextgen2020.comandablue.Service.ProdutoService;
import br.com.nextgen2020.comandablue.model.entidade.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/estabelecimento/{idEstabelecimento}/cardapio/produtos")
    public ResponseEntity<List<Produto>> listarProdutos (
            @PathVariable(value = "idEstabelecimento")Long idEstabelecimento) {

        return ResponseEntity.ok(produtoService.listarProdutos(idEstabelecimento,null));
    }

    @GetMapping("/estabelecimento/{idEstabelecimento}/cardapio/produtos/categoria/{idCategoria}")
    public ResponseEntity<List<Produto>> listarProdutos (
            @PathVariable(value = "idEstabelecimento")Long idEstabelecimento,
            @PathVariable(value = "idCategoria")Long idCategoria) {

        return ResponseEntity.ok(produtoService.listarProdutos(idEstabelecimento,idCategoria));
    }
}
