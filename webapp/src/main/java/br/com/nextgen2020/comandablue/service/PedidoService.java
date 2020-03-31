package br.com.nextgen2020.comandablue.service;

import br.com.nextgen2020.comandablue.model.entidade.Comanda;
import br.com.nextgen2020.comandablue.model.entidade.Pedido;
import br.com.nextgen2020.comandablue.repository.ComandaRepository;
import br.com.nextgen2020.comandablue.repository.PedidoRepository;
import br.com.nextgen2020.comandablue.security.EncryptDecrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private EncryptDecrypt encryptDecrypt;

    public PedidoService() throws Exception {
        encryptDecrypt = new EncryptDecrypt();
    }

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ComandaRepository comandaRepository;

    public List<Pedido> listarPedidos(Long idEstabelecimento, Long idMesa, Long idComanda, String emailEncriptado) throws Exception {
        String emailCliente;
        List<Pedido> pedidoLista;
        List<Pedido> pedidosCliente = new ArrayList<>();
        Optional<Comanda> comanda = comandaRepository.findById(idComanda);

        if(!comanda.isPresent()) {
            return null;
        }

        if(emailEncriptado == null) {
            //pedidoLista = pedidoRepository.findByEstabelecimentoIdAndMesaIdAndComandaId(idEstabelecimento, idMesa, idComanda);
            pedidoLista = comanda.get().getItemPedido();
        }
        else {
            //pedidoLista = pedidoRepository.findByEstabelecimentoIdAndMesaIdAndComandaIdAndEmailId(idEstabelecimento, idMesa, idComanda, idEmail);

             emailCliente = encryptDecrypt.decrypt(emailEncriptado);

            pedidoLista = comanda.get().getItemPedido();
            pedidoLista.forEach(pedido -> {
                if(pedido.getClienteSolicitante().getEmail().equals(emailCliente)) {
                    pedidosCliente.add(pedido);
                }
            });
            return pedidosCliente;
        }

        return pedidoLista;

    }
}
