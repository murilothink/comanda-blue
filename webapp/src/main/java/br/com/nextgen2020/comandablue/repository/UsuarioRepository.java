package br.com.nextgen2020.comandablue.repository;

import br.com.nextgen2020.comandablue.model.entidade.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    public List<Usuario> findByNome(String nome);
    public Usuario findByEmail(String email);

}
