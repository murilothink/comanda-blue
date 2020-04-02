package br.com.nextgen2020.comandablue;

import br.com.nextgen2020.comandablue.model.entidade.*;
import br.com.nextgen2020.comandablue.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@TestComponent
public class DatabaseContext {

    private static DatabaseContext context;

    public static DatabaseContext initializeDatabaseContext(UsuarioRepository usuarioRepository
            , EstabelecimentoRepository estabelecimentoRepository
            , MesaRepository mesaRepository
            , CategoriaProdutoRepository categoriaProdutoRepository
            , ProdutoRepository produtoRepository){
        if(DatabaseContext.context == null){
            context = new DatabaseContext();
            context.usuarioRepository = usuarioRepository;
            context.estabelecimentoRepository = estabelecimentoRepository;
            context.mesaRepository = mesaRepository;
            context.categoriaProdutoRepository = categoriaProdutoRepository;
            context.produtoRepository = produtoRepository;

            context.setUpDatabase();
        }

        return context;
    }

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

    private Estabelecimento estabelecimento;

    private List<Mesa> mesasEstabelecimento;

    public List<Mesa> getMesasEstabelecimento(){
        return this.mesasEstabelecimento;
    }

    public Estabelecimento getEstabelecimento(){
        return this.estabelecimento;
    }

    @PostConstruct
    public void setUpDatabase(){

        // só faz a inicialização a primeira vez // o correto é fazer em nível de classe mas tenho que refatorar
            this.criarUsuarios(5);
            this.estabelecimento = this.criarEstabelecimento();
            this.mesasEstabelecimento = this.criarMesas(this.estabelecimento, 5);
            this.criarProdutos(this.estabelecimento, 3, 4);

    }

    private String getEmailUsuario(int id){
        return String.format("fulano0%s@domain.com", id);
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

}
