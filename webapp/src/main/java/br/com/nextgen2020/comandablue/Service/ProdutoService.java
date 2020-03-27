package br.com.nextgen2020.comandablue.Service;

import br.com.nextgen2020.comandablue.model.entidade.Produto;
import br.com.nextgen2020.comandablue.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarProdutos (Long idEstabelecimento, Long idCategoria){
        List<Produto> produtoMenu;
        if(idCategoria==null) {
            produtoMenu = produtoRepository.findByEstabelecimentoId(idEstabelecimento);
        }else {
            produtoMenu = produtoRepository.findByEstabelecimentoIdAndCategoriaProdutoId(idEstabelecimento, idCategoria);
        }

        return produtoMenu;

    }

}
