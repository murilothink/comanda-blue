package br.com.nextgen2020.comandablue.repository;

import br.com.nextgen2020.comandablue.model.entidade.Mesa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "mesa", path = "mesa")
public interface MesaRepository extends CrudRepository<Mesa, Long> {

     public Mesa findByPin(String Pin);

}
