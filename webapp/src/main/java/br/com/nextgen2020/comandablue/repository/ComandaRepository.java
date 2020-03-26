package br.com.nextgen2020.comandablue.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.nextgen2020.comandablue.model.entidade.Comanda;

public interface ComandaRepository extends CrudRepository<Comanda, Long> {

    List<Comanda> findByMesaIdAndEstabelecimentoId(Long idMesa, Long idEstabelecimento);

}
