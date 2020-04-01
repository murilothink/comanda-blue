package br.com.nextgen2020.comandablue.service;

import br.com.nextgen2020.comandablue.form.PedidoForm;
import br.com.nextgen2020.comandablue.model.entidade.*;
import br.com.nextgen2020.comandablue.model.enums.StatusComanda;
import br.com.nextgen2020.comandablue.repository.*;
import br.com.nextgen2020.comandablue.security.EncryptDecrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private EncryptDecrypt encryptDecrypt;

    public ComandaService() throws Exception {
        encryptDecrypt = new EncryptDecrypt();
    }

    /**
     * Método que busca a mesa pelo pin {id_estabelecimento}-{id_mesa}, e abre uma comanda para a mesa
     * Verifica se há comanda aberta, se houver add o usário na comanda, senão cria uma comanda e add o usuário
     * @param pinMesa
     * @param emailClienteCriptografado
     * @return Comanda aberta ou null caso pin nao seja valido
     */
    public Comanda abrir(String pinMesa, String emailClienteCriptografado) throws Exception{

        if(emailClienteCriptografado.isEmpty()){
            log.info("Abrir comanda recebeu emailClienteCriptografado vazio");
            return null;
        }

        String emailCliente = encryptDecrypt.decrypt(emailClienteCriptografado);

        Mesa mesa = mesaRepository.findByPin(pinMesa);

        if(mesa != null){
            List<Comanda> listaComanda = comandaRepository.findByMesaIdAndEstabelecimentoId(mesa.getId(), mesa.getEstabelecimento().getId());

            for (Comanda comanda : listaComanda){
                if (comanda.getStatus() == StatusComanda.ABERTO){
                    log.info("Encontrada comanda aberta para requisicao pinMesa=" + pinMesa + ", verificando lista usuario...");

                    List<Usuario> listaUsuarios = comanda.getUsuarios();

                    for (Usuario usuario : listaUsuarios){
                        if(usuario.getEmail().equals(emailCliente)){
                            log.info("Usuario ja estava inserido na comanda");
                        }else{
                            log.info("Usuario nao estava inserido na comanda, adicionando usuario... ");
                            listaUsuarios.add(usuarioRepository.findByEmail(emailCliente));
                            comanda.setUsuarios(listaUsuarios);
                        }
                    }

                    return comanda;
                }
            }

            log.info("Nao encontrada comanda aberta para requisicao pinMesa=" + pinMesa + ", criando comanda e adicionando usuario...");
            List<Usuario> listaUsuario = new ArrayList<Usuario>();
            listaUsuario.add(usuarioRepository.findByEmail(emailCliente));

            Comanda comanda = new Comanda(
                    mesa.getEstabelecimento(),
                    mesa,
                    listaUsuario
            );

            comandaRepository.save(comanda);
            return comanda;
        }

        log.info("Mesa nao encontrada ao processar pinMesa=" + pinMesa);

        return null;
    }


    /**
     * Método que faz um pedido de acordo com o id da comanda e o email do cliente passado.
     * Adiciona na lista de pedidos da comanda o novo pedido.
     * @param idComanda
     * @param emailClienteCriptografado
     * @return comanda atualizada
     */
    @Transactional
    public Comanda fazerPedido(Long idComanda, String emailClienteCriptografado, List<PedidoForm> itemPedido, Long idEstabelecimento, Long idMesa) throws Exception {
        String emailDescriptografado = encryptDecrypt.decrypt(emailClienteCriptografado);
        Comanda comanda = comandaRepository.findById(idComanda).get();
        Usuario usuario = usuarioRepository.findByEmail(emailDescriptografado);
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

    public List<Pedido> listarPedidos(Long idComanda, String emailCliente) throws Exception {
        List<Pedido> pedidoLista;
        List<Pedido> pedidosCliente = new ArrayList<>();
        Optional<Comanda> comanda;

        if(emailCliente == null) {
            log.info("email nulo");
            comanda = comandaRepository.findById(idComanda);
        }
        else {
            log.info("Entrou no else");
            comanda = comandaRepository.findByIdAndItemPedidoClienteSolicitanteEmail(idComanda, emailCliente);
            //comanda = comandaRepository.findByItemPedidoClienteSolicitanteEmail(emailCliente);


        }

        if(comanda.isPresent()) {
            return comanda.get().getItemPedido();
        }
        else {
            return new ArrayList<Pedido>();
        }
    }

}