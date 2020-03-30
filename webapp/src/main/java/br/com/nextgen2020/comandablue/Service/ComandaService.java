package br.com.nextgen2020.comandablue.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.nextgen2020.comandablue.form.PedidoForm;
import br.com.nextgen2020.comandablue.model.entidade.*;
import br.com.nextgen2020.comandablue.model.enums.StatusComanda;
import br.com.nextgen2020.comandablue.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    @Transactional
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
     * @param emailCliente
     * @return comanda atualizada
     */
    @Transactional
    public Comanda fazerPedido(Long idComanda, String emailCliente, List<PedidoForm> itemPedido, Long idEstabelecimento, Long idMesa){

        Comanda comanda = comandaRepository.findById(idComanda).get();
        Usuario usuario = usuarioRepository.findByEmail(emailCliente);
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(idEstabelecimento).get();
        Mesa mesa = mesaRepository.findById(idMesa).get();

        //Pega lista de pedidos ja cadastrada da comanda e adiciona os pedidos novos a mais chegados do parametro 'itemPedido'
        List<Pedido> pedidos = comanda.getItemPedido();
        itemPedido.forEach(pedidoForm -> {
            pedidos.add(pedidoForm.converter(usuario, estabelecimento));
        });

        comanda.setEstabelecimento(estabelecimento);
        comanda.setMesa(mesa);
        comanda.setItemPedido(pedidos);

        comandaRepository.save(comanda);
        return comanda;
    }
}