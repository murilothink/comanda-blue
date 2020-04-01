package br.com.nextgen2020.comandablue.repository;

import br.com.nextgen2020.comandablue.model.entidade.Pedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RepositoryRestResource(collectionResourceRel = "pedido", path = "pedido")
public interface PedidoRepository extends CrudRepository<Pedido, Long> {
    /*
    //List<Pedido> findByEstabelecimentoIdAndMesaIdAndComandaId(Long idEstabelecimento, Long idMesa, Long idComanda);
    //List<Pedido> findByEstabelecimentoIdAndMesaIdAndComandaIdAndEmailId(Long idEstabelecimento, Long idMesa, Long idComanda, String emailEncriptado);

    List<Pedido> findByComandaId(Long idComanda);
    List<Pedido> findByComandaIdAndEmailId(Long idComanda, String emailEncriptado);

    List<Pedido> findById(long id);*/
}
