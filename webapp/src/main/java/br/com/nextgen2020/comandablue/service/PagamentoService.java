package br.com.nextgen2020.comandablue.service;

import br.com.nextgen2020.comandablue.model.entidade.Comanda;
import br.com.nextgen2020.comandablue.model.entidade.Pagamento;
import br.com.nextgen2020.comandablue.model.entidade.Pedido;
import br.com.nextgen2020.comandablue.model.entidade.Usuario;
import br.com.nextgen2020.comandablue.repository.ComandaRepository;
import br.com.nextgen2020.comandablue.repository.PagamentoRepository;
import br.com.nextgen2020.comandablue.repository.UsuarioRepository;
import br.com.nextgen2020.comandablue.security.EncryptDecrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ComandaRepository comandaRepository;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private EncryptDecrypt encryptDecrypt;

    public PagamentoService() throws Exception {
        encryptDecrypt = new EncryptDecrypt();
    }


    public Pagamento realizarPagamento(String emailClienteCriptografado, Long idComanda, Double valorPago) throws Exception{

        if(emailClienteCriptografado == null || emailClienteCriptografado.isEmpty()){
            log.info("Email nulo ou vazio, emailClienteCriptografado=" + emailClienteCriptografado);
            return null;
        }

        Optional<Comanda> comanda = comandaRepository.findById(idComanda);

        if(comanda.isPresent()) {
            String emailCliente = encryptDecrypt.decrypt(emailClienteCriptografado);
            Usuario cliente = usuarioRepository.findByEmail(emailCliente);
            if(cliente != null){
                if(comanda.get().getUsuarios().contains(cliente)){
                    Pagamento pagamento = new Pagamento(cliente, comanda.get(), valorPago);
                    pagamentoRepository.save(pagamento);
                    comanda.get().getPagamentos().add(pagamento);
                    comandaRepository.save(comanda.get());
                    return pagamento;
                }else{
                    log.info("Cliente nao consta na lista de usuarios da comanda");
                }
            }else{
                log.info("Cliente nao existe");
            }

        }

        return null;
    }

    public List<Pagamento> listarPagamento(Long idComanda){

        return pagamentoRepository.findByComandaId(idComanda);
    }
}
