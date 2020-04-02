package br.com.nextgen2020.comandablue;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.nextgen2020.comandablue.form.LogarUsuarioForm;
import br.com.nextgen2020.comandablue.model.entidade.*;
import br.com.nextgen2020.comandablue.repository.*;
import br.com.nextgen2020.comandablue.security.EncryptDecrypt;
import br.com.nextgen2020.comandablue.util.WebConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ComandablueApplication.class)
@AutoConfigureMockMvc
// @TestPropertySource(locations = "classpath:application-integrationtest.properties")
@AutoConfigureTestDatabase
public class ComandaBlueIntegratedTests {

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
    private MockMvc mvc;

    private Estabelecimento estabelecimento;

    private List<Mesa> mesasEstabelecimento;

    private static boolean inicializado = false;

    private EncryptDecrypt encryptDecrypt;

    public ComandaBlueIntegratedTests() throws Exception {
        this.encryptDecrypt = new EncryptDecrypt();
    }

    @Before
    public void setUpDatabase(){

        // só faz a inicialização a primeira vez // o correto é fazer em nível de classe mas tenho que refatorar
        if(!inicializado){
            this.criarUsuarios(5);
            this.estabelecimento = this.criarEstabelecimento();
            this.mesasEstabelecimento = this.criarMesas(this.estabelecimento, 5);
            this.criarProdutos(this.estabelecimento, 3, 4);

            ComandaBlueIntegratedTests.inicializado = true;
        }
    }

    private String getEmailUsuario(int id){
        return String.format("fulano0%s@domain.com", id);
    }

    private String getEncryptedEmailUsuario(int id) throws Exception {
       return this.encryptDecrypt.encrypt(this.getEmailUsuario(id));
    }

    private void criarUsuarios(int qtdRegistros){
        for(int i=1; i <= qtdRegistros; i++){
            String nome = "Fulano_" + i;
            String email = this.getEmailUsuario(i);//"fulano0" + i + "@domain.com";
            String senha = "123456";

            Usuario usuario = new Usuario(nome, email, senha);
            this.usuarioRepository.save(usuario);
        }
    }

    private Estabelecimento criarEstabelecimento(){
        Estabelecimento estab = new Estabelecimento("CNPJ123456","Estabelecimento Teste"
                                                 , "Rua Null", "Estabelecimento de testes");
        return this.estabelecimentoRepository.save(estab);
    }

    private List<Mesa> criarMesas(Estabelecimento estabelecimento, int qtdRegistros){
        List<Mesa> mesas = new ArrayList<>();

        for(int i=1; i <= qtdRegistros; i++){
            Mesa mesa = new Mesa(estabelecimento, "Mesa::"+i);
            mesa = this.mesaRepository.save(mesa);
            mesas.add(mesa);
        }

        return mesas;
    }

    private void criarProdutos(Estabelecimento estabelecimento, int qtdCategorias, int qtdProdutosCategoria){
        for(int categoria=1; categoria <= qtdCategorias; categoria++){
            CategoriaProduto catProd = new CategoriaProduto(estabelecimento, "ProdCategoria_0" + categoria);
            catProd = this.categoriaProdutoRepository.save(catProd);

            for(int produto=1; produto <= qtdProdutosCategoria; produto++){
                String nome = "Produto: " + produto;
                double valor = (categoria*10)+produto;
                String unidade = "unidade";
                String descricao = "produto " + categoria + "." + produto;
                Produto prod = new Produto(estabelecimento, nome, valor, descricao, unidade, catProd, "--IMAGEM--");
                prod = this.produtoRepository.save(prod);
            }
        }
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
     * Quando o usuário não existe a aplicação deveria retornar uma erro
     * MAS ela foi configurada pra adicionar o usuário por padrão então
     * não sei dizer se este é a funcionalidade padrão
     * @throws Exception
     */
    @Test
    public void quandoUsuarioNAOCadastrado_EntaoAutenticaErro() throws Exception {

        LogarUsuarioForm usuario99 = this.createUsuarioDataForm(99);

        mvc.perform(post("/usuario/logar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(WebConverter.toJson(usuario99)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void fazerPedidosUmUsuario() throws Exception {
        int idUsuario = 1;
        String usuario1EmailCrypt = this.getEncryptedEmailUsuario(idUsuario);
        String usuarioEmail = this.getEmailUsuario(idUsuario);
        Mesa mesa = this.mesasEstabelecimento.get(3);

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
        Assert.assertEquals("O Estabelecimento não é igual ao solicitado", this.estabelecimento.getCnpj(), comanda.getEstabelecimento().getCnpj());
        Assert.assertEquals(1, comanda.getUsuarios().size());
        Assert.assertEquals(usuarioEmail, comanda.getUsuarios().get(0).getEmail());

        // lista os produtos do estabelecimetno pra fazer pedidos
        List<Produto> produtosEstabelecimento = this.produtoRepository.findByEstabelecimentoId(this.estabelecimento.getId());
        double totalItemsPedidos = 0;



    }

}
