package br.com.nextgen2020.comandablue;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.nextgen2020.comandablue.form.LogarUsuarioForm;
import br.com.nextgen2020.comandablue.form.PedidoForm;
import br.com.nextgen2020.comandablue.model.entidade.*;
import br.com.nextgen2020.comandablue.model.enums.StatusComanda;
import br.com.nextgen2020.comandablue.repository.*;
import br.com.nextgen2020.comandablue.security.EncryptDecrypt;
import br.com.nextgen2020.comandablue.util.WebConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {ComandablueApplication.class,DatabaseContext.class} )
@AutoConfigureMockMvc
// @TestPropertySource(locations = "classpath:application-integrationtest.properties")
@AutoConfigureTestDatabase
public class ComandaBlueIntegratedTests {

    private static final Logger log = LoggerFactory.getLogger(ComandaBlueIntegratedTests.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private CategoriaProdutoRepository categoriaProdutoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ComandaRepository comandaRepository;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private DatabaseContext dataContext;

    private EncryptDecrypt encryptDecrypt;

    public ComandaBlueIntegratedTests() throws Exception {
        this.encryptDecrypt = new EncryptDecrypt();
    }

    private String getEmailUsuario(int id){
        return String.format("fulano0%s@domain.com", id);
    }

    private String getEncryptedEmailUsuario(int id) throws Exception {
        return this.encryptDecrypt.encrypt(this.getEmailUsuario(id));
    }

    private LogarUsuarioForm createUsuarioDataForm(int idUsuario){
        String emailUsuario = this.getEmailUsuario(idUsuario);
        Usuario usuario = this.usuarioRepository.findByEmail(emailUsuario);

        LogarUsuarioForm form = new LogarUsuarioForm();
        if(usuario != null){
            form.setEmail(usuario.getEmail());
            form.setNome(usuario.getNome());
            form.setSenha(usuario.getSenha());
        }
        else{
            form.setEmail(emailUsuario);
            form.setNome(emailUsuario);
            form.setSenha(emailUsuario);
        }

        return form;
    }

    /**
     * Verifica se consegue logar com um usuário cadastrado na base
     * Quando se autentica com um e-mail de um usuário na base de dados
     * ele deve retornar o usuário criptografado e o HTTP Status 200
     * @throws Exception
     */
    @Test
    public void quandoUsuarioCadastrado_EntaoAutenticaOK() throws Exception {

        LogarUsuarioForm usuario1 = this.createUsuarioDataForm(1);

        mvc.perform(post("/usuario/logar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(WebConverter.toJson(usuario1)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Quando o usuário não existir, cadastrar
     * @throws Exception
     */
    @Test
    public void quandoUsuarioNAOCadastrado_EntaoSalvaUsuario() throws Exception {

        LogarUsuarioForm usuario99 = this.createUsuarioDataForm(99);

        mvc.perform(post("/usuario/logar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(WebConverter.toJson(usuario99)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void fazerPedidosUmUsuarioEPagar() throws Exception {
        int idUsuario = 1;
        String usuario1EmailCrypt = this.getEncryptedEmailUsuario(idUsuario);
        String usuarioEmail = this.getEmailUsuario(idUsuario);
        Mesa mesa = this.dataContext.getMesasEstabelecimento().get(11);
        Estabelecimento estabelecimento = this.dataContext.getEstabelecimento();

        String urlAbrirComanda = String.format("/estabelecimento/mesas/%s/comandas/abrir", mesa.getPin());

        ObjectMapper jsonMapper = new ObjectMapper();

        MvcResult result = mvc.perform(post(urlAbrirComanda)
                .contentType("application/x-www-form-urlencoded")
                .header("COMANDA-BLUE-CLIENTE",usuario1EmailCrypt))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Comanda comanda = jsonMapper.readValue(result.getResponse().getContentAsString(), Comanda.class);

        // verifica que o estabelecimento, a mesa e o usuário são quem solicitou a comanda e verifica que só tem ele na comanda
        Assert.assertEquals("A mesa da comanda não é igual a solicitada",mesa.getId(),comanda.getMesa().getId());
        Assert.assertEquals("O Estabelecimento não é igual ao solicitado", estabelecimento.getCnpj(), comanda.getEstabelecimento().getCnpj());
        Assert.assertEquals(1, comanda.getUsuarios().size());
        Assert.assertEquals(usuarioEmail, comanda.getUsuarios().get(0).getEmail());

        // lista os produtos do estabelecimetno pra fazer pedidos
        List<Produto> produtosEstabelecimento = this.produtoRepository.findByEstabelecimentoId(estabelecimento.getId());
        double totalItemsPedidos = 0;

        List<PedidoForm> pedidos = new ArrayList<>();
        pedidos.add(this.createPedido(produtosEstabelecimento.get(2), 1));
        pedidos.add(this.createPedido(produtosEstabelecimento.get(5), 1));
        pedidos.add(this.createPedido(produtosEstabelecimento.get(9), 1));

        for(PedidoForm pedido : pedidos){
            totalItemsPedidos += pedido.getValorTotal();
        }

        String urlFazerPedido = String.format("/estabelecimento/%s/mesas/%s/comandas/%s/pedir", estabelecimento.getId(), mesa.getId(), comanda.getId());

        MvcResult resultFazerPedido = mvc.perform(post(urlFazerPedido)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("COMANDA-BLUE-CLIENTE",usuario1EmailCrypt)
                .content(WebConverter.toJson(pedidos)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        comanda = jsonMapper.readValue(resultFazerPedido.getResponse().getContentAsString(), Comanda.class);

        double valorTotalPedidosRegistrados = 0;

        for(Pedido pedido : comanda.getItemPedido()){
            valorTotalPedidosRegistrados += pedido.getValorTotal();
        }

        Assert.assertEquals(totalItemsPedidos, valorTotalPedidosRegistrados, 0.0001);

        // TESTE PAGAMENTO

        String urlPagarComanda = String.format("/comanda/%s/pagar", comanda.getId());

        MvcResult resultPagarComanda = mvc.perform(post(urlPagarComanda)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("COMANDA-BLUE-CLIENTE",usuario1EmailCrypt)
                .content("{\"valorPago\": "+String.valueOf(valorTotalPedidosRegistrados)+"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Pagamento pagamento = jsonMapper.readValue(resultPagarComanda.getResponse().getContentAsString(), Pagamento.class);

        Assert.assertEquals(valorTotalPedidosRegistrados, pagamento.getValorPago(), 0.0001);

        Comanda comandaFinal = this.comandaRepository.findById(comanda.getId()).get();

        Assert.assertEquals(StatusComanda.FECHADO, comandaFinal.getStatus());

    }

    private PedidoForm createPedido(Produto produto, int qtd){
        PedidoForm pedido = new PedidoForm();
        pedido.setProduto(produto);
        pedido.setQuantidade(qtd);
        pedido.setValorUnitario(produto.getValor());
        pedido.setValorTotal(produto.getValor() * qtd);

        return pedido;
    }

    @Test
    public void multiplosUsuariosParticipandoMesmaComanda() throws Exception {
        Mesa mesa = this.dataContext.getMesasEstabelecimento().get(3);
        Estabelecimento estabelecimento = this.dataContext.getEstabelecimento();

        String urlAbrirComanda = String.format("/estabelecimento/mesas/%s/comandas/abrir", mesa.getPin());

        ObjectMapper jsonMapper = new ObjectMapper();

        for(int i=1; i <= 3; i++){
            int idUsuario = i;
            String usuario1EmailCrypt = this.getEncryptedEmailUsuario(idUsuario);
            String usuarioEmail = this.getEmailUsuario(idUsuario);

            MvcResult result = mvc.perform(post(urlAbrirComanda)
                    .contentType("application/x-www-form-urlencoded")
                    .header("COMANDA-BLUE-CLIENTE", usuario1EmailCrypt))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();

            Comanda comanda = jsonMapper.readValue(result.getResponse().getContentAsString(), Comanda.class);

            // verifica que o estabelecimento, a mesa e o usuário são quem solicitou a comanda e verifica que só tem ele na comanda
            Assert.assertEquals("A mesa da comanda não é igual a solicitada", mesa.getId(), comanda.getMesa().getId());
            Assert.assertEquals("O Estabelecimento não é igual ao solicitado", estabelecimento.getCnpj(), comanda.getEstabelecimento().getCnpj());
            Assert.assertEquals(i, comanda.getUsuarios().size());
            Assert.assertEquals(usuarioEmail, comanda.getUsuarios().get(i-1).getEmail());

        }


      }

    @Test
    public void mesmoUsuarioEntrandoNaMesmaMesaMultiplasVezes () throws Exception {
        Estabelecimento estabelecimento = this.dataContext.getEstabelecimento();

        Mesa mesa = this.dataContext.getMesasEstabelecimento().get(0);
        String urlAbrirComanda = String.format("/estabelecimento/mesas/%s/comandas/abrir", mesa.getPin());

        int idUsuario = 1;
        String usuario1EmailCrypt = this.getEncryptedEmailUsuario(idUsuario);
        String usuarioEmail = this.getEmailUsuario(idUsuario);

        ObjectMapper jsonMapper = new ObjectMapper();

        for(int i=1; i <= 4; i++){

            log.info(String.format("Abrir comanda usuario %s | %s",usuarioEmail, urlAbrirComanda));

            MvcResult result = mvc.perform(post(urlAbrirComanda)
                    .contentType("application/x-www-form-urlencoded")
                    .header("COMANDA-BLUE-CLIENTE", usuario1EmailCrypt))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();

            Comanda comanda = jsonMapper.readValue(result.getResponse().getContentAsString(), Comanda.class);

            // verifica que o estabelecimento, a mesa e o usuário são quem solicitou a comanda e verifica que só tem ele na comanda
            Assert.assertEquals("A mesa da comanda não é igual a solicitada", mesa.getId(), comanda.getMesa().getId());
            Assert.assertEquals("O Estabelecimento não é igual ao solicitado", estabelecimento.getCnpj(), comanda.getEstabelecimento().getCnpj());
            Assert.assertEquals(1, comanda.getUsuarios().size());
            Assert.assertEquals(usuarioEmail, comanda.getUsuarios().get(0).getEmail());

        }
    }


    @Test
    public void mesmoUsuarioEmMultiplasComandas() throws Exception {
        Estabelecimento estabelecimento = this.dataContext.getEstabelecimento();


        ObjectMapper jsonMapper = new ObjectMapper();

        for(int i=1; i <= 4; i++){
            int idUsuario = 1;
            String usuario1EmailCrypt = this.getEncryptedEmailUsuario(idUsuario);
            String usuarioEmail = this.getEmailUsuario(idUsuario);
            Mesa mesa = this.dataContext.getMesasEstabelecimento().get(i);

            String urlAbrirComanda = String.format("/estabelecimento/mesas/%s/comandas/abrir", mesa.getPin());

            log.info(String.format("Abrir comanda usuario %s | %s",usuarioEmail, urlAbrirComanda));

            MvcResult result = mvc.perform(post(urlAbrirComanda)
                    .contentType("application/x-www-form-urlencoded")
                    .header("COMANDA-BLUE-CLIENTE", usuario1EmailCrypt))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();

            Comanda comanda = jsonMapper.readValue(result.getResponse().getContentAsString(), Comanda.class);

            // verifica que o estabelecimento, a mesa e o usuário são quem solicitou a comanda e verifica que só tem ele na comanda
            Assert.assertEquals("A mesa da comanda não é igual a solicitada", mesa.getId(), comanda.getMesa().getId());
            Assert.assertEquals("O Estabelecimento não é igual ao solicitado", estabelecimento.getCnpj(), comanda.getEstabelecimento().getCnpj());
            Assert.assertEquals(1, comanda.getUsuarios().size());
            Assert.assertEquals(usuarioEmail, comanda.getUsuarios().get(0).getEmail());

        }


    }

    }
