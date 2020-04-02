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


        mesa = new Mesa(barDoZe, "Interno 2");
        this.mesaRepository.save(mesa);


        mesa = new Mesa(barDoZe, "Interno 8");
        this.mesaRepository.save(mesa);


        mesa = new Mesa(barDoZe, "Lado Janela A");
        this.mesaRepository.save(mesa);





        Estabelecimento barDoLu = estabelecimentoRepository.findByCnpj("98765432109876");

        Mesa mesaBarDoLu = new Mesa(barDoLu, "Mesa da janela 2");
        this.mesaRepository.save(mesaBarDoLu); // salvar primeira vez para salvar id

        mesaBarDoLu = new Mesa(barDoLu, "Mesa janela 3");
        this.mesaRepository.save(mesaBarDoLu);

        mesaBarDoLu = new Mesa(barDoLu, "Mesa janela 4");
        this.mesaRepository.save(mesaBarDoLu);

        mesaBarDoLu = new Mesa(barDoLu, "Mesa janela 5");
        this.mesaRepository.save(mesaBarDoLu);

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
                9.50,
                "Bolinhas de Queijos artesanais de alta qualidade c/10 un",
                "Unidade",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Salgados", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://img.itdg.com.br/tdg/images/recipes/000/165/285/284443/284443_original.jpg?mode=crop&width=710&height=400"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "Esfiha",
                5.50,
                "Esfiha de varios sabores deliciosos",
                "Unidade",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Salgados", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://img.itdg.com.br/tdg/images/recipes/000/196/100/294232/294232_original.jpg?mode=crop&width=710&height=400"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "Coxinhas de Frango",
                5.20,
                "Deliciosa coxinha de frango",
                "Unidade",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Salgados", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://cdn.guiadacozinha.com.br/wp-content/uploads/2019/10/coxinha-batata-catupiry.jpg"
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
                "Porção de Camarão",
                41.50,
                "Deliciosos Camarão bem fritos e crocantes",
                "Unidade",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Porçoes", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://www.meubarato.com/image/d2lkdGg9NjYwJmZvcmNlPTEmcmVmcmVzaD0xNDY5NjM3NTcwJnE9OTgmc3JjPWltZy9vZmVydGFzL29mZXJ0YV8yOTA4LmpwZyZzZWN1cmU9e2J1cm59.jpg"
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

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "Porção de Kibe",
                25.50,
                "Deliciosos Kibes",
                "Unidade",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Porçoes", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://i.ytimg.com/vi/0SicsEzXwFM/maxresdefault.jpg"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "FRG a Passarinho",
                26.90,
                "Deliciosa porção de Frango a Passarinho",
                "unidade",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Porçoes", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://img.stpu.com.br/?img=https://s3.amazonaws.com/pu-mgr/default/a0R0f00000zSm5YEAS/5b3a99d1e4b0f15190213827.jpg&w=710&h=462"
        ));


        //PRODUTOS DO TIPO LANCHE

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "Mortadela com Queijo",
                20.00,
                "pão frances em tamanho especial mortadela e queijo prato.",
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

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "File mignon",
                17.00,
                "Pão de hamburguer, alface, queijo, tomate e hamburguer",
                "Unidade",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Lanches", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://lh3.googleusercontent.com/proxy/8f-erI083sgEHSyPRrfQlqNK5aqmJ5fVY-KdNtxeWSGsJ1C-iXMb9Mnoxvznj2rUOgz1sByt9_6VVao4BbIgFcFaePj2uvxbfqWVTiNsdYxNwImyIOmbANRvP_jCGMS06YU"
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
                "Agua 500ml",
                2.50,
                "Agua Crystal sem gás",
                "unidade",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Bebidas", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://static.carrefour.com.br/medias/sys_master/images/images/hde/h2a/h00/h00/13985115275294.jpg"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "Agua 500ml",
                2.50,
                "Agua Crystal com gás",
                "unidade",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Bebidas", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://www.euqueroservip.com.br/sitevip/vip-profiles/uploads/2019/09/agua-mineral-crystal-com-gas-500ml-600x600.jpg"
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

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "51 Famosa agua de bar",
                6.50,
                "A melhor agua de bar da região",
                "1LT",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Bebidas", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://static.carrefour.com.br/medias/sys_master/images/images/h51/h33/h00/h00/9393719541790.jpg"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "Coca Cola 2LT",
                7.50,
                "Melhor bebida do mundo e muito gelada",
                "2LT",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Bebidas", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://www.kerokery.com.br/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/k/k/kkr-_176-sku-870___bebidas__coca-cola-2l.jpg"
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
                "Suco de Graviola",
                9.00,
                "Um delicioso suco de Graviola",
                "Copo",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Sucos Naturais", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://www.mundoboaforma.com.br/wp-content/uploads/2017/06/suco-de-graviola-1280x720.jpg"
        ));


        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "Suco de Morango Com Leite",
                8.50,
                "Um delicioso suco de Mor. com leite integral",
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


        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "Suco de Limão",
                5.50,
                "Um delicioso suco de limão",
                "Copo",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Sucos Naturais", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://www.mundoboaforma.com.br/wp-content/uploads/2017/08/suco-de-limao-1280x720.jpg"
        ));














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
                9.50,
                "Bolinhas de Queijos artesanais de alta qualidade c/10 un",
                "Unidade",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Salgados", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://img.itdg.com.br/tdg/images/recipes/000/165/285/284443/284443_original.jpg?mode=crop&width=710&height=400"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "Esfiha",
                5.50,
                "Esfiha de varios sabores deliciosos",
                "Unidade",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Salgados", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://img.itdg.com.br/tdg/images/recipes/000/196/100/294232/294232_original.jpg?mode=crop&width=710&height=400"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "Coxinhas de Frango",
                5.20,
                "Deliciosa coxinha de frango",
                "Unidade",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Salgados", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://cdn.guiadacozinha.com.br/wp-content/uploads/2019/10/coxinha-batata-catupiry.jpg"
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
                "Porção de Camarão",
                41.50,
                "Deliciosos Camarão bem fritos e crocantes",
                "Unidade",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Porçoes", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://www.meubarato.com/image/d2lkdGg9NjYwJmZvcmNlPTEmcmVmcmVzaD0xNDY5NjM3NTcwJnE9OTgmc3JjPWltZy9vZmVydGFzL29mZXJ0YV8yOTA4LmpwZyZzZWN1cmU9e2J1cm59.jpg"
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

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "Porção de Kibe",
                25.50,
                "Deliciosos Kibes",
                "Unidade",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Porçoes", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://i.ytimg.com/vi/0SicsEzXwFM/maxresdefault.jpg"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "FRG a Passarinho",
                26.90,
                "Deliciosa porção de Frango a Passarinho",
                "unidade",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Porçoes", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://img.stpu.com.br/?img=https://s3.amazonaws.com/pu-mgr/default/a0R0f00000zSm5YEAS/5b3a99d1e4b0f15190213827.jpg&w=710&h=462"
        ));

        //PRODUTOS DO TIPO LANCHE

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "Mortadela com Queijo",
                20.00,
                "pão frances em tamanho especial mortadela e queijo prato.",
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

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "File mignon",
                17.00,
                "Pão de hamburguer, alface, queijo, tomate e hamburguer",
                "Unidade",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Lanches", estabelecimentoRepository.findByCnpj("12345678901234")),
                "https://lh3.googleusercontent.com/proxy/8f-erI083sgEHSyPRrfQlqNK5aqmJ5fVY-KdNtxeWSGsJ1C-iXMb9Mnoxvznj2rUOgz1sByt9_6VVao4BbIgFcFaePj2uvxbfqWVTiNsdYxNwImyIOmbANRvP_jCGMS06YU"
        ));

        //PRODUTOS DO TIPO BEBIDA

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("98765432109876"),
                "Bavaria 350ml",
                3.50,
                "A verdadeira puro malte, sangue de rodeio, super gelada",
                "lata",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Bebidas", estabelecimentoRepository.findByCnpj("98765432109876")),
                "https://www.paodeacucar.com/img/uploads/1/17/619017.png?type=product"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("98765432109876"),
                "Agua 500ml",
                2.50,
                "Agua Crystal sem gás",
                "unidade",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Bebidas", estabelecimentoRepository.findByCnpj("98765432109876")),
                "https://static.carrefour.com.br/medias/sys_master/images/images/hde/h2a/h00/h00/13985115275294.jpg"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("98765432109876"),
                "Agua 500ml",
                2.50,
                "Agua Crystal com gás",
                "unidade",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Bebidas", estabelecimentoRepository.findByCnpj("98765432109876")),
                "https://www.euqueroservip.com.br/sitevip/vip-profiles/uploads/2019/09/agua-mineral-crystal-com-gas-500ml-600x600.jpg"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("98765432109876"),
                "Brahma 350ml",
                4.50,
                "A Queridinha da galera chegou ",
                "lata",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Bebidas", estabelecimentoRepository.findByCnpj("98765432109876")),
                "https://hiperideal.vteximg.com.br/arquivos/ids/171241-1000-1000/469599.jpg?v=636624279132030000"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("98765432109876"),
                "Brahma 1LT",
                7.50,
                "A famosa litrão esta a sua espera",
                "lata",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Bebidas", estabelecimentoRepository.findByCnpj("98765432109876")),
                "https://formulachopp.cdn.3techstore.com.br/img/p/5/7/3/573-large_default.jpg"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("98765432109876"),
                "Chopp de Vinho",
                14.00,
                "Uma deliciosa taça de Chopp de vinho ",
                "Taça",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Bebidas", estabelecimentoRepository.findByCnpj("98765432109876")),
                "https://choppcuritiba.com.br/wp-content/uploads/2019/06/chopp-de-vinho.jpg"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("98765432109876"),
                "Batida Kapetinha",
                9.50,
                "Batida a base de morango, chamyto, limão e bastante vodka",
                "Copo",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Bebidas", estabelecimentoRepository.findByCnpj("98765432109876")),
                "https://i.pinimg.com/originals/d0/66/2b/d0662be441cd0e5a86b8d22cc6f8264d.jpg"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("98765432109876"),
                "Espumante Chandon",
                9.50,
                "Espumante gourmet de qualidade",
                "Dose",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Bebidas", estabelecimentoRepository.findByCnpj("98765432109876")),
                "https://static.glamurama.uol.com.br/2014/01/NotaChandon1.jpg"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("98765432109876"),
                "51 Famosa agua de bar",
                6.50,
                "A melhor agua de bar da região",
                "1LT",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Bebidas", estabelecimentoRepository.findByCnpj("98765432109876")),
                "https://static.carrefour.com.br/medias/sys_master/images/images/h51/h33/h00/h00/9393719541790.jpg"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("98765432109876"),
                "Coca Cola 2LT",
                7.50,
                "Melhor bebida do mundo e muito gelada",
                "2LT",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Bebidas", estabelecimentoRepository.findByCnpj("98765432109876")),
                "https://www.kerokery.com.br/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/k/k/kkr-_176-sku-870___bebidas__coca-cola-2l.jpg"
        ));

        //PRODUTOS DO TIPO SUCO NATURAIS

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("98765432109876"),
                "Suco de Laranja",
                7.00,
                "Um delicioso suco de laranjas escolhidas",
                "Copo",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Sucos Naturais", estabelecimentoRepository.findByCnpj("98765432109876")),
                "https://img.elo7.com.br/product/zoom/262F374/adesivo-parede-decoracao-suco-de-laranja-fruta-lanchonete-adesivo-decorativo.jpg"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("98765432109876"),
                "Suco de Graviola",
                9.00,
                "Um delicioso suco de Graviola",
                "Copo",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Sucos Naturais", estabelecimentoRepository.findByCnpj("98765432109876")),
                "https://www.mundoboaforma.com.br/wp-content/uploads/2017/06/suco-de-graviola-1280x720.jpg"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("98765432109876"),
                "Suco de Morango Com Leite",
                8.50,
                "Um delicioso suco de Mor. com leite integral",
                "Copo",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Sucos Naturais", estabelecimentoRepository.findByCnpj("98765432109876")),
                "https://cozinhalucrativa.com/wp-content/uploads/2018/04/suco-de-Morango-com-Leite-Condensado-simples-facil-veja-como-fazer-cozinha-lucrativa-2018.jpg"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("98765432109876"),
                "Suco de Maracuja",
                7.50,
                "Um delicioso suco de polpa de maracuja",
                "Copo",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Sucos Naturais", estabelecimentoRepository.findByCnpj("98765432109876")),
                "https://felizsaude.com.br/wp-content/uploads/2019/12/sucode.jpg"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("98765432109876"),
                "Suco de Limão",
                5.50,
                "Um delicioso suco de polpa de maracuja",
                "Copo",
                categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Sucos Naturais", estabelecimentoRepository.findByCnpj("98765432109876")),
                "https://www.mundoboaforma.com.br/wp-content/uploads/2017/08/suco-de-limao-1280x720.jpg"
        ));

    }

    private void insertEstabelecimento() {
        this.estabelecimentoRepository.save(
                new Estabelecimento("12345678901234", "Bar do zé",
                        "Rua da Santa Missa, 840, Centro, Leme/SP",
                        "Desde 1978 servindo cerveja gelada e o melhor torresmo de Leme"));

        this.estabelecimentoRepository.save(
                new Estabelecimento("98765432109876","Bar do Lu",
                        "Em algum lugar do mundo",
                        "desde 1985 alegrando os stags"));
    }


    private void insertCategoriaProduto() {
        Estabelecimento barDoZe = estabelecimentoRepository.findByCnpj("12345678901234");
        this.categoriaProdutoRepository.save(new CategoriaProduto(barDoZe, "Salgados"));
        this.categoriaProdutoRepository.save(new CategoriaProduto(barDoZe, "Lanches"));
        this.categoriaProdutoRepository.save(new CategoriaProduto(barDoZe, "Bebidas"));
        this.categoriaProdutoRepository.save(new CategoriaProduto(barDoZe, "Sucos Naturais"));
        this.categoriaProdutoRepository.save(new CategoriaProduto(barDoZe, "Porçoes"));



        Estabelecimento barDoLu = estabelecimentoRepository.findByCnpj("98765432109876");
        this.categoriaProdutoRepository.save(new CategoriaProduto(barDoLu, "Salgados"));
        this.categoriaProdutoRepository.save(new CategoriaProduto(barDoLu, "Lanches"));
        this.categoriaProdutoRepository.save(new CategoriaProduto(barDoLu, "Bebidas"));
        this.categoriaProdutoRepository.save(new CategoriaProduto(barDoLu, "Sucos Naturais"));
        this.categoriaProdutoRepository.save(new CategoriaProduto(barDoLu, "Porçoes"));


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
