package br.com.nextgen2020.comandablue.service;

import br.com.nextgen2020.comandablue.model.entidade.Pagamento;
import br.com.nextgen2020.comandablue.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public List<Pagamento> listarPagamento(Long idComanda){

        return pagamentoRepository.findByComandaId(idComanda);
    }
}
