package br.com.nextgen2020.comandablue;


import br.com.nextgen2020.comandablue.model.entidade.*;
import br.com.nextgen2020.comandablue.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component // automatically picked up by @SpringBootApplication
// CommandLineRunner, runs after all the beans are created and registered.
public class DatabaseLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;
    private final MesaRepository mesaRepository;
    private final CategoriaProdutoRepository categoriaProdutoRepository;
    private final ProdutoRepository produtoRepository;

    @Autowired
    public DatabaseLoader(UsuarioRepository usuarioRepository,
                          CategoriaProdutoRepository categoriaProdutoRepository,
                          EstabelecimentoRepository estabelecimentoRepository,
                          ProdutoRepository produtoRepository,
                          MesaRepository mesaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.estabelecimentoRepository = estabelecimentoRepository;
        this.categoriaProdutoRepository = categoriaProdutoRepository;
        this.produtoRepository = produtoRepository;
        this.mesaRepository = mesaRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        insertUsuario();
        insertEstabelecimento();
        insertMesa();
        insertCategoriaProduto();
        insertProduto();
    }

    private void insertMesa() {
        Estabelecimento barDoZe = estabelecimentoRepository.findByCnpj("12345678901234");

        Mesa mesa = new Mesa(barDoZe, "Deck lago 3");
        this.mesaRepository.save(mesa); // salvar primeira vez para salvar id
        mesa.setPin();
        this.mesaRepository.save(mesa); // salvar segunda vez para salvar pin gerado

        mesa = new Mesa(barDoZe, "Deck lago 7");
        this.mesaRepository.save(mesa);
        mesa.setPin();
        this.mesaRepository.save(mesa);

        mesa = new Mesa(barDoZe, "Interno 2");
        this.mesaRepository.save(mesa);
        mesa.setPin();
        this.mesaRepository.save(mesa);

        mesa = new Mesa(barDoZe, "Interno 8");
        this.mesaRepository.save(mesa);
        mesa.setPin();
        this.mesaRepository.save(mesa);
    }

    private void insertProduto() {

        this.produtoRepository.save(new Produto(
                        estabelecimentoRepository.findByCnpj("12345678901234"),
                        "Torresmo frito",
                        12.50,
                        "Torresmo frito no óleo, acompanha molho especial",
                        "unidade",
                        categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Salgados", estabelecimentoRepository.findByCnpj("12345678901234")),
                        "https://s2.glbimg.com/_7nmXnmXFpsLqPBlgnx4TySsOMo=/e.glbimg.com/og/ed/f/original/2019/06/19/torresmo1.jpg"
                        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "Bavaria",
                3.50,
                "A verdadeira puro malte, sangue de rodeio, super gelada",
                "lata",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Bebidas", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://www.paodeacucar.com/img/uploads/1/17/619017.png?type=product"
        ));
    }

    private void insertEstabelecimento() {
        this.estabelecimentoRepository.save(
                new Estabelecimento("12345678901234", "Bar do zé",
                        "Rua da Santa Missa, 840, Centro, Leme/SP",
                        "Desde 1978 servindo cerveja gelada e o melhor torresmo de Leme"));
    }

    private void insertCategoriaProduto() {
        Estabelecimento barDoZe = estabelecimentoRepository.findByCnpj("12345678901234");
        this.categoriaProdutoRepository.save(new CategoriaProduto(barDoZe, "Salgados"));
        this.categoriaProdutoRepository.save(new CategoriaProduto(barDoZe, "Lanches"));
        this.categoriaProdutoRepository.save(new CategoriaProduto(barDoZe, "Bebidas"));
    }

    public void insertUsuario(){
        this.usuarioRepository.save(new Usuario("Danilo de Nadai Sicari", "denadai.sicari@gmail.com", "12345"));
        this.usuarioRepository.save(new Usuario("Erik Kenzo Oura Carlini Valle", "erik@ciandt.com", "12345"));
        this.usuarioRepository.save(new Usuario("Cristiano Andrade de Aguiar", "aguiar@ciandt.com", "12345"));
        this.usuarioRepository.save(new Usuario("João Pedro Benicio", "jbenicio@ciandt.com", "12345"));
        this.usuarioRepository.save(new Usuario("Luciano Alves dos Santos", "lucianos@ciandt.com", "12345"));
        this.usuarioRepository.save(new Usuario("Luciano Henrique Arendt Rodrigues", "lucianohr@ciandt.com", "12345"));
        this.usuarioRepository.save(new Usuario("Murilo Henrique Gomes", "murilohg@ciandt.com", "12345"));
        this.usuarioRepository.save(new Usuario("Rafael Sperandio Scheiner", "rscheiner@ciandt.com", "12345"));
        this.usuarioRepository.save(new Usuario("Renato Vinicius dos Santos", "renatods@ciandt.com", "12345"));
        this.usuarioRepository.save(new Usuario("Tiago Fernandes Tasselli", "tiagotasselli@ciandt.com", "12345"));
    }
}
