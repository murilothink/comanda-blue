package br.com.nextgen2020.comandablue.repository;

import br.com.nextgen2020.comandablue.model.entidade.CategoriaProduto;
import br.com.nextgen2020.comandablue.model.entidade.Estabelecimento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")
@RepositoryRestResource(collectionResourceRel = "categoriaproduto", path = "categoriaproduto")
public interface CategoriaProdutoRepository extends CrudRepository<CategoriaProduto, Long> {

    public CategoriaProduto findByCategoriaAndEstabelecimento(String categoria, Estabelecimento estabelecimento);

}
