package br.com.nextgen2020.comandablue.Service;

import java.util.ArrayList;
import java.util.List;

import br.com.nextgen2020.comandablue.model.entidade.Pedido;
import br.com.nextgen2020.comandablue.model.entidade.Usuario;
import br.com.nextgen2020.comandablue.model.enums.StatusComanda;
import br.com.nextgen2020.comandablue.repository.EstabelecimentoRepository;
import br.com.nextgen2020.comandablue.repository.MesaRepository;
import br.com.nextgen2020.comandablue.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nextgen2020.comandablue.model.entidade.Comanda;
import br.com.nextgen2020.comandablue.repository.ComandaRepository;
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


    public Comanda fazerPedido(Long idComanda, List<Pedido> itemPedido, String emailCliente){
        return null;
    }
}
