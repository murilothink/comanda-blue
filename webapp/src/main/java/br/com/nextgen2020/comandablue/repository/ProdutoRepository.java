package br.com.nextgen2020.comandablue.repository;

import br.com.nextgen2020.comandablue.model.entidade.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RepositoryRestResource(collectionResourceRel = "produto", path = "produto")
public interface ProdutoRepository extends CrudRepository<Produto, Long> {
    List<Produto> findByEstabelecimentoId (Long idEstabelecimento);
    List<Produto> findByEstabelecimentoIdAndCategoriaProdutoId (Long idEstabelecimento, Long idCategoria);

    List<Produto> findById(long i);
}