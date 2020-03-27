package br.com.nextgen2020.comandablue.Service;

import java.util.ArrayList;
import java.util.List;

import br.com.nextgen2020.comandablue.model.entidade.*;
import br.com.nextgen2020.comandablue.model.enums.StatusComanda;
import br.com.nextgen2020.comandablue.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComandaService {
    @Autowired
    private ComandaRepository comandaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;
    @Autowired
    private MesaRepository mesaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    private static final Logger log = LoggerFactory.getLogger(ComandaService.class);

    /**
     * Método que busca a mesa pelo id do estabelecimento, e abrir uma comanda para a mesa
     * Verifica se há comanda aberta, se houver add o usário na comanda, senão cria uma comanda e add o usuário
     * @param idEstabelecimento
     * @param idMesa
     * @return
     */
    public Comanda abrir(Long idEstabelecimento, Long idMesa, String emailCliente){
        List<Comanda> listaComanda = comandaRepository.findByMesaIdAndEstabelecimentoId(idMesa, idEstabelecimento);

        for (Comanda comanda : listaComanda){
            if (comanda.getStatus() == StatusComanda.ABERTO){
                List<Usuario> listaUsuarios = comanda.getUsuarios();
                listaUsuarios.add(usuarioRepository.findByEmail(emailCliente));
                comanda.setUsuarios(listaUsuarios);

                return comanda;
            }
        }

        List<Usuario> listaUsuario = new ArrayList<Usuario>();
        listaUsuario.add(usuarioRepository.findByEmail(emailCliente));

        Comanda comanda = new Comanda(
                estabelecimentoRepository.findById(idEstabelecimento).get(),
                mesaRepository.findById(idMesa).get(),
                listaUsuario
                );

        comandaRepository.save(comanda);
        return comanda;
    }


    /**
     * Método que faz um pedido de acordo com o id da comanda e o email do cliente passado.
     * Adiciona na lista de pedidos da comanda o novo pedido.
     * @param idComanda
     * @param itemPedido
     * @param emailCliente
     * @return comanda atualizada
     */
    public Comanda fazerPedido(Long idComanda,
                               List<Pedido> itemPedido,
                               String emailCliente,
                               Long idEstabelecimento,
                               Long idMesa){

        Comanda comanda = comandaRepository.findById(idComanda).get();

        Estabelecimento estabelecimento = estabelecimentoRepository.findById(idEstabelecimento).get();

        Mesa mesa = mesaRepository.findById(idMesa).get();

        List<Usuario> listaUsuario = comanda.getUsuarios();

        List<Pedido> listaPedido = comanda.getItemPedido();
        listaPedido.addAll(itemPedido);

        comanda.setEstabelecimento(estabelecimento);
        comanda.setMesa(mesa);
        comanda.setUsuarios(listaUsuario);
        comanda.setItemPedido(listaPedido);
        comandaRepository.save(comanda);

        log.info(comanda.toString());
        return comanda;
    }
}