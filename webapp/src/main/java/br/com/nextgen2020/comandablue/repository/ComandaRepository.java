package br.com.nextgen2020.comandablue.repository;

import br.com.nextgen2020.comandablue.model.entidade.Comanda;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ComandaRepository extends CrudRepository<Comanda, Long> {

    List<Comanda> findByMesaIdAndEstabelecimentoId(Long idMesa, Long idEstabelecimento);

    Optional<Comanda> findByIdAndItemPedidoClienteSolicitanteEmail(Long idComanda, String email);
}
