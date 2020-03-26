package br.com.nextgen2020.comandablue.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.nextgen2020.comandablue.model.entidade.Comanda;
import br.com.nextgen2020.comandablue.repository.ComandaRepository;
@Service
public class ComandaService {

    private ComandaRepository comandaRepository;

    /**
     * Método que busca a mesa pelo id do estabelecimento, e abrir uma comanda para a mesa
     * Verifica se há comanda aberta, se houver add o usário na comanda, senão cria uma comanda e add o usuário
     * @param idEstabelecimento
     * @param idMesa
     * @return
     */
    public Comanda abrir(Long idEstabelecimento, Long idMesa, String emailCliente){
        //todo
        List<Comanda> listaComanda = comandaRepository.findByMesaIdAndEstabelecimentoId(idEstabelecimento, idMesa);

        listaComanda.forEach((x) ->{

        });

        return null;
    }

}
