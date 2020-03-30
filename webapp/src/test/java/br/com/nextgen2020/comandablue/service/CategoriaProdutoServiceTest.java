//package br.com.nextgen2020.comandablue.service;
//
//import br.com.nextgen2020.comandablue.form.CategoriasForm;
//import br.com.nextgen2020.comandablue.model.entidade.CategoriaProduto;
//import br.com.nextgen2020.comandablue.model.entidade.Estabelecimento;
//import br.com.nextgen2020.comandablue.model.entidade.Produto;
//import br.com.nextgen2020.comandablue.repository.CategoriaProdutoRepository;
//import br.com.nextgen2020.comandablue.repository.ProdutoRepository;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@RunWith(SpringRunner.class)
//public class CategoriaProdutoServiceTest {
//
//    /*@TestConfiguration
//    static class CategoriaProdutoServiceTestContextConfiguration {
//
//        @Bean
//        public CategoriaProdutoService categoriaProdutoService() {
//            return new CategoriaProdutoService();
//        }
//    }
//
//    @Autowired
//    private CategoriaProdutoService categoriaProdutoService;
//
//    @MockBean
//    private ProdutoRepository produtoRepository;
//
//    @MockBean
//    private CategoriaProdutoRepository categoriaProdutoRepository;
//
//
//    private Estabelecimento estabelecimento;
//    private List<Produto> produtosCategoria1;
//    private List<Produto> todosProdutos;
//    @Before
//    public void setUp() {
//        this.estabelecimento = new Estabelecimento("1234","Algum Lugar", "Lugar nenhum", "Um bar qualquer");
//        this.estabelecimento.setId(1L);
//        Long categoriaProduto1 = 1L;
//        Long categoriaProduto2 = 2L;
//
//        this.produtosCategoria1 = new ArrayList<>();
//        this.produtosCategoria1.add(this.createProduto(this.estabelecimento, categoriaProduto1));
//        this.produtosCategoria1.add(this.createProduto(this.estabelecimento, categoriaProduto1));
//        this.produtosCategoria1.add(this.createProduto(this.estabelecimento, categoriaProduto1));
//
//        List<Produto> produtosCategoria2 = new ArrayList<>();
//        produtosCategoria2.add(this.createProduto(this.estabelecimento, categoriaProduto2));
//        produtosCategoria2.add(this.createProduto(this.estabelecimento, categoriaProduto2));
//
//        this.todosProdutos = new ArrayList<>();
//        this.todosProdutos.addAll(this.produtosCategoria1);
//        this.todosProdutos.addAll(produtosCategoria2);
//
//        Mockito.when(produtoRepository.findByEstabelecimentoId(estabelecimento.getId())).thenReturn(this.todosProdutos);
//        Mockito.when(produtoRepository.findByEstabelecimentoIdAndCategoriaProdutoId(estabelecimento.getId(), categoriaProduto1)).thenReturn(this.produtosCategoria1);
//        Mockito.when(produtoRepository.findByEstabelecimentoIdAndCategoriaProdutoId(estabelecimento.getId(), categoriaProduto2)).thenReturn(produtosCategoria2);
//    }
//
//    private Produto createProduto(Estabelecimento estabelecimento, long idCategoria){
//        CategoriaProduto categoria = new CategoriaProduto(estabelecimento, "categoria:" + idCategoria);
//        categoria.setId(idCategoria);
//        return new Produto(estabelecimento, "produto", 100.05, "mesmo produto", "unidade", categoria, "imagem");
//    }
//
//
//    @Test
//    public void quandoIdEstabelecimentoForValido() {
//        Long estabelecimentoId = 1L;
//
//        //Montagem do valor esperado
//        CategoriasForm categoriasFormEsperado = new CategoriasForm();
//        categoriasFormEsperado.setEstabelecimento(this.estabelecimento);
//
//        CategoriaProduto categoria1 = new CategoriaProduto(this.estabelecimento, "categoria:" + 1L);
//        categoria1.setId(1L);
//        CategoriaProduto categoria2 = new CategoriaProduto(this.estabelecimento, "categoria:" + 2L);
//        categoria2.setId(2L);
//
//        List<CategoriaProduto> categorias = new ArrayList<CategoriaProduto>(){{
//            add(categoria1);
//            add(categoria2);
//        }};
//
//        categoriasFormEsperado.setCategorias(categorias);
//        //--------------------------------------------------
//
//        CategoriasForm categoriasForm = this.categoriaProdutoService.listarCategorias(estabelecimentoId);
//
//        assertEquals(categoriasFormEsperado, categoriasForm);
//    }
//    */
//}
