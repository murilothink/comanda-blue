package br.com.nextgen2020.comandablue.repository;

import br.com.nextgen2020.comandablue.model.entidade.Mesa;
import br.com.nextgen2020.comandablue.model.entidade.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "mesa", path = "mesa")
public interface MesaRepository extends CrudRepository<Mesa, Long> {
     public Mesa findByPIN(String PIN);

}
