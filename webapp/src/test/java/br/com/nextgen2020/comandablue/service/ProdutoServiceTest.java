package br.com.nextgen2020.comandablue.service;

import br.com.nextgen2020.comandablue.model.entidade.CategoriaProduto;
import br.com.nextgen2020.comandablue.model.entidade.Estabelecimento;
import br.com.nextgen2020.comandablue.model.entidade.Produto;
import br.com.nextgen2020.comandablue.repository.ProdutoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class ProdutoServiceTest {


    @TestConfiguration
    static class ProdutoServiceTestContextConfiguration {

        @Bean
        public ProdutoService produtoService() {
            return new ProdutoService();
        }
    }

    @Autowired
    private ProdutoService produtoService;

    @MockBean
    private ProdutoRepository produtoRepository;

    private Estabelecimento estabelecimento;
    private List<Produto> produtosCategoria1;
    private List<Produto> todosProdutos;


    @Before
    public void setUp() {
        this.estabelecimento = new Estabelecimento("1234","Algum Lugar", "Lugar nenhum", "Um bar qualquer");
        this.estabelecimento.setId(1L);
        Long categoriaProduto1 = 1L;
        Long categoriaProduto2 = 2L;

        this.produtosCategoria1 = new ArrayList<>();
        this.produtosCategoria1.add(this.createProduto(this.estabelecimento, categoriaProduto1));
        this.produtosCategoria1.add(this.createProduto(this.estabelecimento, categoriaProduto1));
        this.produtosCategoria1.add(this.createProduto(this.estabelecimento, categoriaProduto1));

        List<Produto> produtosCategoria2 = new ArrayList<>();
        produtosCategoria2.add(this.createProduto(this.estabelecimento, categoriaProduto2));
        produtosCategoria2.add(this.createProduto(this.estabelecimento, categoriaProduto2));

        this.todosProdutos = new ArrayList<>();
        this.todosProdutos.addAll(this.produtosCategoria1);
        this.todosProdutos.addAll(produtosCategoria2);

        Mockito.when(produtoRepository.findByEstabelecimentoId(estabelecimento.getId())).thenReturn(this.todosProdutos);
        Mockito.when(produtoRepository.findByEstabelecimentoIdAndCategoriaProdutoId(estabelecimento.getId(), categoriaProduto1)).thenReturn(this.produtosCategoria1);
        Mockito.when(produtoRepository.findByEstabelecimentoIdAndCategoriaProdutoId(estabelecimento.getId(), categoriaProduto2)).thenReturn(produtosCategoria2);
    }

    private Produto createProduto(Estabelecimento estabelecimento, long idCategoria){
        CategoriaProduto categoria = new CategoriaProduto(estabelecimento, "categoria:" + idCategoria);
        categoria.setId(idCategoria);
        return new Produto(estabelecimento, "produto", 100.05, "mesmo produto", "unidade", categoria, "imagem");
    }

    /**
     * Dado que o estabelecimento é ID[1] e não é informado nenhuma categoria de produto pra busca
     * quando o método listarProdutos for chamado
     * então ele deve retornar todos os produtos para aquele estabelecimento
     *
     */
    @Test
    public void quandoCategoriaNulaEntaoRetornaTodosProdutos() {
        Long estabelecimentoId = this.estabelecimento.getId();

        List<Produto> produtosRetornados = this.produtoService.listarProdutos(estabelecimentoId, null);

        assertEquals(this.todosProdutos.size(), produtosRetornados.size());
    }

    /**
     * Dado que o estabelecimento ID[1] e a categoria ID[1]
     * quando listamos produtos por estes parâmetros
     * então somente os produtos da categoria 1 são retornados
     */
    @Test
    public void quandoCategoria1EntaoRetornaProdutosCategoria1(){
        Long categoriaProduto1 = this.produtosCategoria1.get(0).getCategoriaProduto().getId();

        List<Produto> produtosRetornados = this.produtoService.listarProdutos(this.estabelecimento.getId(), categoriaProduto1);

        assertEquals(this.produtosCategoria1.size(), produtosRetornados.size());
    }

    /**
     * Dado que o estabelecimento ID[1] e a categoria não existe na base ID[99]
     * quando listamos produtos por estes parâmetros
     * então será retornada uma lista vazia de produtos
     */
    @Test
    public void quandoCategoriaNaoExistenteEntaoRetornaListaVazia(){
        Long categoriaProdutoNaoExistente = 99L;

        List<Produto> produtosRetornados = this.produtoService.listarProdutos(this.estabelecimento.getId(), categoriaProdutoNaoExistente);

        assertTrue(produtosRetornados.isEmpty());
    }

}