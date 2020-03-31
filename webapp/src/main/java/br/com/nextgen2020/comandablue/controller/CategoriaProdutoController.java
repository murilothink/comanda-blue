package br.com.nextgen2020.comandablue.controller;

import br.com.nextgen2020.comandablue.service.CategoriaProdutoService;
import br.com.nextgen2020.comandablue.form.CategoriasForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriaProdutoController {

    @Autowired
    private CategoriaProdutoService categoriaProdutoService;

    @GetMapping("estabelecimento/{idEstabelecimento}/categorias")
    public ResponseEntity<CategoriasForm> listarCategorias(
            @PathVariable(name = "idEstabelecimento") Long idEstabelecimento
    ){
        CategoriasForm categorias = categoriaProdutoService.listarCategorias(idEstabelecimento);

        if(categorias != null){
            return ResponseEntity.ok(categorias);
        }

        return ResponseEntity.badRequest().build();
    }
}
