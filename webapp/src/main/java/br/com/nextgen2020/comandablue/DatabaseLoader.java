package br.com.nextgen2020.comandablue;


import br.com.nextgen2020.comandablue.model.entidade.*;
import br.com.nextgen2020.comandablue.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
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
        // arrumei a propriedade Pin para ser uma propriedade calculada então não há necessidade de salvar 2 vezes
//        mesa.setPin();
//        this.mesaRepository.save(mesa); // salvar segunda vez para salvar pin gerado

        mesa = new Mesa(barDoZe, "Deck lago 7");
        this.mesaRepository.save(mesa);
//        mesa.setPin();
//        this.mesaRepository.save(mesa);

        mesa = new Mesa(barDoZe, "Interno 2");
        this.mesaRepository.save(mesa);
//        mesa.setPin();
//        this.mesaRepository.save(mesa);

        mesa = new Mesa(barDoZe, "Interno 8");
        this.mesaRepository.save(mesa);
//        mesa.setPin();
//        this.mesaRepository.save(mesa);

        mesa = new Mesa(barDoZe, "Lado Janela A");
        this.mesaRepository.save(mesa);
//        mesa.setPin();
//        this.mesaRepository.save(mesa);

//        Estabelecimento calegaris = estabelecimentoRepository.findByCnpj("54255906000186");
//
//        Mesa mesaCalegaris = new Mesa(calegaris, "Mesa do Restaurante");
//        this.mesaRepository.save(mesaCalegaris); // salvar primeira vez para salvar id
//        mesaCalegaris.setPin();
//        this.mesaRepository.save(mesaCalegaris); // salvar segunda vez para salvar pin gerado
    }

    private void insertProduto() {

        //PRODUTOS DO TIPO SALGADOS

        this.produtoRepository.save(new Produto(
                        estabelecimentoRepository.findByCnpj("12345678901234"),
                        "Torresmo frito",
                        12.50,
                        "Torresmo frito no óleo, acompanha molho especial",
                        "KG",
                        categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Salgados", estabelecimentoRepository.findByCnpj("12345678901234")),
                        "https://s2.glbimg.com/_7nmXnmXFpsLqPBlgnx4TySsOMo=/e.glbimg.com/og/ed/f/original/2019/06/19/torresmo1.jpg"
                        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "Bolinhas de Queijo",
                0.50,
                "Bolinhas de Queijos artesanais de alta qualidade",
                "Unidade",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Salgados", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://img.itdg.com.br/tdg/images/recipes/000/165/285/284443/284443_original.jpg?mode=crop&width=710&height=400"
        ));


        //PRODUTOS DO TIPO PORÇÕES

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "Batata Frita",
                11.50,
                "Deliciosas batatas fritas crocantes",
                "Unidade",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Porçoes", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://img.itdg.com.br/tdg/images/recipes/000/018/897/164773/164773_original.jpg?mode=crop&width=710&height=400"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "Calabresa Acebolada",
                35.50,
                "Deliciosas Calabresa na cebola",
                "Unidade",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Porçoes", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://www.anamariabrogui.com.br/assets/uploads/receitas/fotos/usuario-2024-1ad9369edbeb228f064158ebfa6ae528.jpg"
        ));


        //PRODUTOS DO TIPO LANCHE

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "Mortadela com Queijo",
                20.00,
                "pão frances em tamanho especial mortadela, queijo prato, maionese temperada, tomate e rúcula.",
                "Unidade",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Lanches", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://media-cdn.tripadvisor.com/media/photo-s/0c/08/5a/a0/lanche-de-mortadela-com.jpg"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "X Cheddar",
                11.00,
                "Pão de hamburguer, maionese, 2 hamburgueres, cheddar e cebola empanada.",
                "Unidade",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Lanches", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://static.vix.com/pt/sites/default/files/styles/1x1/public/h/hamburguer-queijo-cheddar-fast-food-0918-1400x800.jpg"
        ));


        //PRODUTOS DO TIPO BEBIDA

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "Bavaria 350ml",
                3.50,
                "A verdadeira puro malte, sangue de rodeio, super gelada",
                "lata",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Bebidas", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://www.paodeacucar.com/img/uploads/1/17/619017.png?type=product"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "Brahma 350ml",
                4.50,
                "A Queridinha da galera chegou ",
                "lata",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Bebidas", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://hiperideal.vteximg.com.br/arquivos/ids/171241-1000-1000/469599.jpg?v=636624279132030000"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "Brahma 1LT",
                7.50,
                "A famosa litrão esta a sua espera",
                "lata",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Bebidas", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://formulachopp.cdn.3techstore.com.br/img/p/5/7/3/573-large_default.jpg"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "Chopp de Vinho",
                14.00,
                "Uma deliciosa taça de Chopp de vinho ",
                "Taça",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Bebidas", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://choppcuritiba.com.br/wp-content/uploads/2019/06/chopp-de-vinho.jpg"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "Batida Kapetinha",
                9.50,
                "Batida a base de morango, chamyto, limão e bastante vodka",
                "Copo",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Bebidas", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://i.pinimg.com/originals/d0/66/2b/d0662be441cd0e5a86b8d22cc6f8264d.jpg"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "Espumante Chandon",
                9.50,
                "Espumante gourmet de qualidade",
                "Dose",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Bebidas", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://static.glamurama.uol.com.br/2014/01/NotaChandon1.jpg"
        ));


        //PRODUTOS DO TIPO SUCO NATURAIS

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "Suco de Laranja",
                7.00,
                "Um delicioso suco de laranjas escolhidas",
                "Copo",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Sucos Naturais", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://img.elo7.com.br/product/zoom/262F374/adesivo-parede-decoracao-suco-de-laranja-fruta-lanchonete-adesivo-decorativo.jpg"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "Suco de Morango Com Leite",
                8.50,
                "Um delicioso suco de Morango com leite integral",
                "Copo",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Sucos Naturais", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://cozinhalucrativa.com/wp-content/uploads/2018/04/suco-de-Morango-com-Leite-Condensado-simples-facil-veja-como-fazer-cozinha-lucrativa-2018.jpg"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "Suco de Maracuja",
                7.50,
                "Um delicioso suco de polpa de maracuja",
                "Copo",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Sucos Naturais", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://felizsaude.com.br/wp-content/uploads/2019/12/sucode.jpg"
        ));



    }

    private void insertEstabelecimento() {
        this.estabelecimentoRepository.save(
                new Estabelecimento("12345678901234", "Bar do zé",
                        "Rua da Santa Missa, 840, Centro, Leme/SP",
                        "Desde 1978 servindo cerveja gelada e o melhor torresmo de Leme"));
//        this.estabelecimentoRepository.save(
//                new Estabelecimento("54255906000186","calegaris",
//                        "general osorio paulinia-sp",
//                        "desde 1985 alegrando paulinia"));
    }


    private void insertCategoriaProduto() {
        Estabelecimento barDoZe = estabelecimentoRepository.findByCnpj("12345678901234");
        this.categoriaProdutoRepository.save(new CategoriaProduto(barDoZe, "Salgados"));
        this.categoriaProdutoRepository.save(new CategoriaProduto(barDoZe, "Lanches"));
        this.categoriaProdutoRepository.save(new CategoriaProduto(barDoZe, "Bebidas"));
        this.categoriaProdutoRepository.save(new CategoriaProduto(barDoZe, "Sucos Naturais"));
        this.categoriaProdutoRepository.save(new CategoriaProduto(barDoZe, "Porçoes"));



//        Estabelecimento calegaris = estabelecimentoRepository.findByCnpj("54255906000186");
//        this.categoriaProdutoRepository.save(new CategoriaProduto(calegaris, "Padaria"));
//        this.categoriaProdutoRepository.save(new CategoriaProduto(calegaris, "Bebidas"));
//        this.categoriaProdutoRepository.save(new CategoriaProduto(calegaris, "Açougue"));

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
