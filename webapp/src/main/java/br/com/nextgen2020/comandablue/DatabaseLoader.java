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
        insertProdutoBarDoZe();
        insertProdutoBarDoLu();
    }

    private void insertMesa() {

        Mesa mesa;

        // Salva mesas barDoZe

        Estabelecimento barDoZe = estabelecimentoRepository.findByCnpj("12345678901234");

        mesa = new Mesa(barDoZe, "Deck lago 3");
        this.mesaRepository.save(mesa);

        mesa = new Mesa(barDoZe, "Deck lago 7");
        this.mesaRepository.save(mesa);

        mesa = new Mesa(barDoZe, "Interno 2");
        this.mesaRepository.save(mesa);

        mesa = new Mesa(barDoZe, "Interno 8");
        this.mesaRepository.save(mesa);

        mesa = new Mesa(barDoZe, "Lado Janela A");
        this.mesaRepository.save(mesa);




        // Salva mesas do barDoLu

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

    private void insertProdutoBarDoZe() {

        /* ----- Insercao produto no barDoZe -----*/

        Estabelecimento barDoZe = estabelecimentoRepository.findByCnpj("12345678901234");

        //PRODUTOS DO TIPO SALGADOS

        CategoriaProduto categoria = categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Salgados", barDoZe);

        this.produtoRepository.save(new Produto(
                barDoZe,
                "Torresmo frito",
                12.50,
                "Torresmo frito no óleo, acompanha molho especial",
                "KG",
                categoria,
                "https://s2.glbimg.com/_7nmXnmXFpsLqPBlgnx4TySsOMo=/e.glbimg.com/og/ed/f/original/2019/06/19/torresmo1.jpg"
        ));

        this.produtoRepository.save(new Produto(
                estabelecimentoRepository.findByCnpj("12345678901234"),
                "Bolinhas de Queijo",
                9.50,
                "Bolinhas de Queijos artesanais de alta qualidade c/10 un",
                "Unidade",
                categoria,
                "https://img.itdg.com.br/tdg/images/recipes/000/165/285/284443/284443_original.jpg?mode=crop&width=710&height=400"
        ));

        this.produtoRepository.save(new Produto(
                barDoZe,
                "Esfiha",
                5.50,
                "Esfiha de varios sabores deliciosos",
                "Unidade",
                categoria,
                "https://img.itdg.com.br/tdg/images/recipes/000/196/100/294232/294232_original.jpg?mode=crop&width=710&height=400"
        ));

        this.produtoRepository.save(new Produto(
                barDoZe,
                "Coxinhas de Frango",
                5.20,
                "Deliciosa coxinha de frango",
                "Unidade",
                categoria,
                "https://cdn.guiadacozinha.com.br/wp-content/uploads/2019/10/coxinha-batata-catupiry.jpg"
        ));


        //PRODUTOS DO TIPO PORÇÕES

        CategoriaProduto categoria2 = categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Porçoes", barDoZe);

        this.produtoRepository.save(new Produto(
                barDoZe,
                "Batata Frita",
                11.50,
                "Deliciosas batatas fritas crocantes",
                "Unidade",
                categoria2,
                "https://img.itdg.com.br/tdg/images/recipes/000/018/897/164773/164773_original.jpg?mode=crop&width=710&height=400"
        ));

        this.produtoRepository.save(new Produto(
                barDoZe,
                "Porção de Camarão",
                41.50,
                "Deliciosos Camarão bem fritos e crocantes",
                "Unidade",
                categoria2,
                "https://www.meubarato.com/image/d2lkdGg9NjYwJmZvcmNlPTEmcmVmcmVzaD0xNDY5NjM3NTcwJnE9OTgmc3JjPWltZy9vZmVydGFzL29mZXJ0YV8yOTA4LmpwZyZzZWN1cmU9e2J1cm59.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoZe,
                "Calabresa Acebolada",
                35.50,
                "Deliciosas Calabresa na cebola",
                "Unidade",
                categoria2,
                "https://www.anamariabrogui.com.br/assets/uploads/receitas/fotos/usuario-2024-1ad9369edbeb228f064158ebfa6ae528.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoZe,
                "Porção de Kibe",
                25.50,
                "Deliciosos Kibes",
                "Unidade",
                categoria2,
                "https://i.ytimg.com/vi/0SicsEzXwFM/maxresdefault.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoZe,
                "FRG a Passarinho",
                26.90,
                "Deliciosa porção de Frango a Passarinho",
                "unidade",
                categoria2,
                "https://img.stpu.com.br/?img=https://s3.amazonaws.com/pu-mgr/default/a0R0f00000zSm5YEAS/5b3a99d1e4b0f15190213827.jpg&w=710&h=462"
        ));


        //PRODUTOS DO TIPO LANCHE
        CategoriaProduto categoria3 = categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Lanches", barDoZe);

        this.produtoRepository.save(new Produto(
                barDoZe,
                "Mortadela com Queijo",
                20.00,
                "pão frances em tamanho especial mortadela e queijo prato.",
                "Unidade",
                categoria3,
                "https://media-cdn.tripadvisor.com/media/photo-s/0c/08/5a/a0/lanche-de-mortadela-com.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoZe,
                "X Cheddar",
                11.00,
                "Pão de hamburguer, maionese, 2 hamburgueres, cheddar e cebola empanada.",
                "Unidade",
                categoria3,
                "https://static.vix.com/pt/sites/default/files/styles/1x1/public/h/hamburguer-queijo-cheddar-fast-food-0918-1400x800.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoZe,
                "File mignon",
                17.00,
                "Pão de hamburguer, alface, queijo, tomate e hamburguer",
                "Unidade",
                categoria3,
                "https://lh3.googleusercontent.com/proxy/7hWGbsDTToNdIVsRG1GKnyJeUxVx6sJJvusIWAOgBYCk5kBdmRZcjbagSRvgQEcFYYaBRkp6_ZraZdVshmr9ZmGeUNuWwGM5v6UGJPSbNhRUBrWm3jc"
        ));


        //PRODUTOS DO TIPO BEBIDA
        CategoriaProduto categoria4 = categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Bebidas", barDoZe);

        this.produtoRepository.save(new Produto(
                barDoZe,
                "Bavaria 350ml",
                3.50,
                "A verdadeira puro malte, sangue de rodeio, super gelada",
                "lata",
                categoria4,
                "https://www.paodeacucar.com/img/uploads/1/17/619017.png?type=product"
        ));

        this.produtoRepository.save(new Produto(
                barDoZe,
                "Agua 500ml",
                2.50,
                "Agua Crystal sem gás",
                "unidade",
                categoria4,
                "https://static.carrefour.com.br/medias/sys_master/images/images/hde/h2a/h00/h00/13985115275294.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoZe,
                "Agua 500ml",
                2.50,
                "Agua Crystal com gás",
                "unidade",
                categoria4,
                "https://www.euqueroservip.com.br/sitevip/vip-profiles/uploads/2019/09/agua-mineral-crystal-com-gas-500ml-600x600.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoZe,
                "Brahma 350ml",
                4.50,
                "A Queridinha da galera chegou ",
                "lata",
                categoria4,
                "https://hiperideal.vteximg.com.br/arquivos/ids/171241-1000-1000/469599.jpg?v=636624279132030000"
        ));

        this.produtoRepository.save(new Produto(
                barDoZe,
                "Brahma 1LT",
                7.50,
                "A famosa litrão está a sua espera",
                "lata",
                categoria4,
                "https://formulachopp.cdn.3techstore.com.br/img/p/5/7/3/573-large_default.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoZe,
                "Chopp de Vinho",
                14.00,
                "Uma deliciosa taça de Chopp de vinho ",
                "Taça",
                categoria4,
                "https://choppcuritiba.com.br/wp-content/uploads/2019/06/chopp-de-vinho.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoZe,
                "Batida Kapetinha",
                9.50,
                "Batida a base de morango, chamyto, limão e bastante vodka",
                "Copo",
                categoria4,
                "https://i.pinimg.com/originals/d0/66/2b/d0662be441cd0e5a86b8d22cc6f8264d.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoZe,
                "Espumante Chandon",
                9.50,
                "Espumante gourmet de qualidade",
                "Dose",
                categoria4,
                "https://static.glamurama.uol.com.br/2014/01/NotaChandon1.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoZe,
                "51 Famosa agua de bar",
                6.50,
                "A melhor agua de bar da região",
                "1LT",
                categoria4,
                "https://static.carrefour.com.br/medias/sys_master/images/images/h51/h33/h00/h00/9393719541790.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoZe,
                "Coca Cola 2LT",
                7.50,
                "Melhor bebida do mundo e muito gelada",
                "2LT",
                categoria4,
                "https://www.kerokery.com.br/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/k/k/kkr-_176-sku-870___bebidas__coca-cola-2l.jpg"
        ));




        //PRODUTOS DO TIPO SUCO NATURAIS
        CategoriaProduto categoria5 = categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Sucos Naturais", barDoZe);

        this.produtoRepository.save(new Produto(
                barDoZe,
                "Suco de Laranja",
                7.00,
                "Um delicioso suco de laranjas escolhidas",
                "Copo",
                categoria5,
                "https://img.elo7.com.br/product/zoom/262F374/adesivo-parede-decoracao-suco-de-laranja-fruta-lanchonete-adesivo-decorativo.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoZe,
                "Suco de Graviola",
                9.00,
                "Um delicioso suco de Graviola",
                "Copo",
                categoria5,
                "https://www.mundoboaforma.com.br/wp-content/uploads/2017/06/suco-de-graviola-1280x720.jpg"
        ));


        this.produtoRepository.save(new Produto(
                barDoZe,
                "Suco de Morango Com Leite",
                8.50,
                "Um delicioso suco de Mor. com leite integral",
                "Copo",
                categoria5,
                "https://cozinhalucrativa.com/wp-content/uploads/2018/04/suco-de-Morango-com-Leite-Condensado-simples-facil-veja-como-fazer-cozinha-lucrativa-2018.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoZe,
                "Suco de Maracuja",
                7.50,
                "Um delicioso suco de  maracuja",
                "Copo",
                categoria5,
                "https://felizsaude.com.br/wp-content/uploads/2019/12/sucode.jpg"
        ));


        this.produtoRepository.save(new Produto(
                barDoZe,
                "Suco de Limão",
                5.50,
                "Um delicioso suco de limão",
                "Copo",
                categoria5,
                "https://www.mundoboaforma.com.br/wp-content/uploads/2017/08/suco-de-limao-1280x720.jpg"
        ));

    }




    private void insertProdutoBarDoLu(){
        /* ----- Insercao produto no barDoLu -----*/

        Estabelecimento barDoLu = estabelecimentoRepository.findByCnpj("98765432109876");

        //PRODUTOS DO TIPO SALGADOS

        CategoriaProduto categoria = categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Salgados", barDoLu);

        //PRODUTOS DO TIPO SALGADOS

        this.produtoRepository.save(new Produto(
                barDoLu,
                "Torresmo frito",
                12.50,
                "Torresmo frito no óleo, acompanha molho especial",
                "KG",
                categoria,
                "https://s2.glbimg.com/_7nmXnmXFpsLqPBlgnx4TySsOMo=/e.glbimg.com/og/ed/f/original/2019/06/19/torresmo1.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoLu,
                "Bolinhas de Queijo",
                9.50,
                "Bolinhas de Queijos artesanais de alta qualidade c/10 un",
                "Unidade",
                categoria,
                "https://img.itdg.com.br/tdg/images/recipes/000/165/285/284443/284443_original.jpg?mode=crop&width=710&height=400"
        ));

        this.produtoRepository.save(new Produto(
                barDoLu,
                "Esfiha",
                5.50,
                "Esfiha de varios sabores deliciosos",
                "Unidade",
                categoria,
                "https://img.itdg.com.br/tdg/images/recipes/000/196/100/294232/294232_original.jpg?mode=crop&width=710&height=400"
        ));

        this.produtoRepository.save(new Produto(
                barDoLu,
                "Coxinhas de Frango",
                5.20,
                "Deliciosa coxinha de frango",
                "Unidade",
                categoria,
                "https://cdn.guiadacozinha.com.br/wp-content/uploads/2019/10/coxinha-batata-catupiry.jpg"
        ));

        //PRODUTOS DO TIPO PORÇÕES
        CategoriaProduto categoria2 = categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Porçoes", barDoLu);

        this.produtoRepository.save(new Produto(
                barDoLu,
                "Batata Frita",
                11.50,
                "Deliciosas batatas fritas crocantes",
                "Unidade",
                categoria2,
                "https://img.itdg.com.br/tdg/images/recipes/000/018/897/164773/164773_original.jpg?mode=crop&width=710&height=400"
        ));

        this.produtoRepository.save(new Produto(
                barDoLu,
                "Porção de Camarão",
                41.50,
                "Deliciosos Camarão bem fritos e crocantes",
                "Unidade",
                categoria2,
                "https://www.meubarato.com/image/d2lkdGg9NjYwJmZvcmNlPTEmcmVmcmVzaD0xNDY5NjM3NTcwJnE9OTgmc3JjPWltZy9vZmVydGFzL29mZXJ0YV8yOTA4LmpwZyZzZWN1cmU9e2J1cm59.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoLu,
                "Calabresa Acebolada",
                35.50,
                "Deliciosas Calabresa na cebola",
                "Unidade",
                categoria2,
                "https://www.anamariabrogui.com.br/assets/uploads/receitas/fotos/usuario-2024-1ad9369edbeb228f064158ebfa6ae528.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoLu,
                "Porção de Kibe",
                25.50,
                "Deliciosos Kibes",
                "Unidade",
                categoria2,
                "https://i.ytimg.com/vi/0SicsEzXwFM/maxresdefault.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoLu,
                "FRG a Passarinho",
                26.90,
                "Deliciosa porção de Frango a Passarinho",
                "unidade",
                categoria2,
                "https://img.stpu.com.br/?img=https://s3.amazonaws.com/pu-mgr/default/a0R0f00000zSm5YEAS/5b3a99d1e4b0f15190213827.jpg&w=710&h=462"
        ));

        //PRODUTOS DO TIPO LANCHE
        CategoriaProduto categoria3 = categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Lanches", barDoLu);

        this.produtoRepository.save(new Produto(
                barDoLu,
                "Mortadela com Queijo",
                20.00,
                "pão frances em tamanho especial mortadela e queijo prato.",
                "Unidade",
                categoria3,
                "https://media-cdn.tripadvisor.com/media/photo-s/0c/08/5a/a0/lanche-de-mortadela-com.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoLu,
                "X Cheddar",
                11.00,
                "Pão de hamburguer, maionese, 2 hamburgueres, cheddar e cebola empanada.",
                "Unidade",
                categoria3,
                "https://static.vix.com/pt/sites/default/files/styles/1x1/public/h/hamburguer-queijo-cheddar-fast-food-0918-1400x800.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoLu,
                "File mignon",
                17.00,
                "Pão de hamburguer, alface, queijo, tomate e hamburguer",
                "Unidade",
                categoria3,
                "https://lh3.googleusercontent.com/proxy/7hWGbsDTToNdIVsRG1GKnyJeUxVx6sJJvusIWAOgBYCk5kBdmRZcjbagSRvgQEcFYYaBRkp6_ZraZdVshmr9ZmGeUNuWwGM5v6UGJPSbNhRUBrWm3jc"
        ));

        //PRODUTOS DO TIPO BEBIDA
        CategoriaProduto categoria4 = categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Bebidas", barDoLu);

        this.produtoRepository.save(new Produto(
                barDoLu,
                "Bavaria 350ml",
                3.50,
                "A verdadeira puro malte, sangue de rodeio, super gelada",
                "lata",
                categoria4,
                "https://www.paodeacucar.com/img/uploads/1/17/619017.png?type=product"
        ));

        this.produtoRepository.save(new Produto(
                barDoLu,
                "Agua 500ml",
                2.50,
                "Agua Crystal sem gás",
                "unidade",
                categoria4,
                "https://static.carrefour.com.br/medias/sys_master/images/images/hde/h2a/h00/h00/13985115275294.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoLu,
                "Agua 500ml",
                2.50,
                "Agua Crystal com gás",
                "unidade",
                categoria4,
                "https://www.euqueroservip.com.br/sitevip/vip-profiles/uploads/2019/09/agua-mineral-crystal-com-gas-500ml-600x600.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoLu,
                "Brahma 350ml",
                4.50,
                "A Queridinha da galera chegou ",
                "lata",
                categoria4,
                "https://hiperideal.vteximg.com.br/arquivos/ids/171241-1000-1000/469599.jpg?v=636624279132030000"
        ));

        this.produtoRepository.save(new Produto(
                barDoLu,
                "Brahma 1LT",
                7.50,
                "A famosa litrão esta a sua espera",
                "lata",
                categoria4,
                "https://formulachopp.cdn.3techstore.com.br/img/p/5/7/3/573-large_default.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoLu,
                "Chopp de Vinho",
                14.00,
                "Uma deliciosa taça de Chopp de vinho ",
                "Taça",
                categoria4,
                "https://choppcuritiba.com.br/wp-content/uploads/2019/06/chopp-de-vinho.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoLu,
                "Batida Kapetinha",
                9.50,
                "Batida a base de morango, chamyto, limão e bastante vodka",
                "Copo",
                categoria4,
                "https://i.pinimg.com/originals/d0/66/2b/d0662be441cd0e5a86b8d22cc6f8264d.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoLu,
                "Espumante Chandon",
                9.50,
                "Espumante gourmet de qualidade",
                "Dose",
                categoria4,
                "https://static.glamurama.uol.com.br/2014/01/NotaChandon1.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoLu,
                "51 Famosa agua de bar",
                6.50,
                "A melhor agua de bar da região",
                "1LT",
                categoria4,
                "https://static.carrefour.com.br/medias/sys_master/images/images/h51/h33/h00/h00/9393719541790.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoLu,
                "Coca Cola 2LT",
                7.50,
                "Melhor bebida do mundo e muito gelada",
                "2LT",
                categoria4,
                "https://www.kerokery.com.br/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/k/k/kkr-_176-sku-870___bebidas__coca-cola-2l.jpg"
        ));

        //PRODUTOS DO TIPO SUCO NATURAIS
        CategoriaProduto categoria5 = categoriaProdutoRepository.findByCategoriaAndEstabelecimento("Sucos Naturais", barDoLu);

        this.produtoRepository.save(new Produto(
                barDoLu,
                "Suco de Laranja",
                7.00,
                "Um delicioso suco de laranjas escolhidas",
                "Copo",
                categoria5,
                "https://img.elo7.com.br/product/zoom/262F374/adesivo-parede-decoracao-suco-de-laranja-fruta-lanchonete-adesivo-decorativo.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoLu,
                "Suco de Graviola",
                9.00,
                "Um delicioso suco de Graviola",
                "Copo",
                categoria5,
                "https://www.mundoboaforma.com.br/wp-content/uploads/2017/06/suco-de-graviola-1280x720.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoLu,
                "Suco de Morango Com Leite",
                8.50,
                "Um delicioso suco de Mor. com leite integral",
                "Copo",
                categoria5,
                "https://cozinhalucrativa.com/wp-content/uploads/2018/04/suco-de-Morango-com-Leite-Condensado-simples-facil-veja-como-fazer-cozinha-lucrativa-2018.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoLu,
                "Suco de Maracuja",
                7.50,
                "Um delicioso suco de polpa de maracuja",
                "Copo",
                categoria5,
                "https://felizsaude.com.br/wp-content/uploads/2019/12/sucode.jpg"
        ));

        this.produtoRepository.save(new Produto(
                barDoLu,
                "Suco de Limão",
                5.50,
                "Um delicioso suco de polpa de maracuja",
                "Copo",
                categoria5,
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
