package br.com.nextgen2020.comandablue.service;

import br.com.nextgen2020.comandablue.form.PedidoForm;
import br.com.nextgen2020.comandablue.model.entidade.CategoriaProduto;
import br.com.nextgen2020.comandablue.model.entidade.Comanda;
import br.com.nextgen2020.comandablue.model.entidade.Estabelecimento;
import br.com.nextgen2020.comandablue.model.entidade.Produto;
import br.com.nextgen2020.comandablue.repository.ComandaRepository;
import br.com.nextgen2020.comandablue.security.EncryptDecrypt;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

public class ComandaServiceTest {
    @TestConfiguration
    static class ComandaServiceTestContextConfiguration {

        @Bean
        public ComandaService comandaService() throws Exception {
            return new ComandaService();
        }
    }

    @Autowired
    private ComandaService comandaService;

    @MockBean
    private ComandaRepository comandaRepository;

    private Estabelecimento estabelecimento;
    private Comanda comanda;

    @Before
    public void setUp() {
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


            this.estabelecimento = new Estabelecimento();
            this.estabelecimento.setId(1L);

            this.comanda = new Comanda();
            this.comanda.setId(1L);
//
//
//         Mockito.when(comandaRepository.findById(estabelecimento.getId())).thenReturn(this.comanda);
//        Mockito.when(produtoRepository.findByEstabelecimentoIdAndCategoriaProdutoId(estabelecimento.getId(), categoriaProduto1)).thenReturn(this.produtosCategoria1);
//        Mockito.when(produtoRepository.findByEstabelecimentoIdAndCategoriaProdutoId(estabelecimento.getId(), categoriaProduto2)).thenReturn(produtosCategoria2);
}

    @Test
    public void geraEmailCriptografado() throws Exception {
        EncryptDecrypt encryptDecrypt = new EncryptDecrypt();

        String emailAberto = "murilohg@ciandt.com";
        String emailCriptografado = encryptDecrypt.encrypt(emailAberto);

        System.out.println(emailCriptografado);
    }

//  public Comanda fazerPedido(Long idComanda, String emailCliente, List<PedidoForm> itemPedido, Long idEstabelecimento, Long idMesa){
//    @Test
//    public void quandoComandaFechadaRetornaErro() throws Exception{
//        Long idComanda = 1L;
//        String emailCliente = "aguiar@ciandt.com";
//        List<PedidoForm> itemPedido = criaPedidos();
//        Long idEstabelecimento = 1L;
//        Long idMesa = 1L;
//
//        this.comandaService.fazerPedido(idComanda, emailCliente, itemPedido, idEstabelecimento, idMesa);
//    }

    private PedidoForm pedidoForm(){
        PedidoForm pedidoForm = new PedidoForm();

        pedidoForm.setProduto(produto());
        pedidoForm.setObservacao("asdasdasd");
        pedidoForm.setQuantidade(2);
        pedidoForm.setValorTotal((double) 10);
        pedidoForm.setValorUnitario((double) 5);

        return pedidoForm;
    }

    private Produto produto(){
        Produto produto = new Produto();

        produto.setCategoriaProduto(categoria());
        produto.setDescricao("asdasdasdasd");
        produto.setId(1L);
        produto.setImagemDoProduto("https://p.bigstockphoto.com/GeFvQkBbSLaMdpKXF1Zv_bigstock-Aerial-View-Of-Blue-Lakes-And--227291596.jpg");
        produto.setNome("Coxinha");
        produto.setUnidade("2");
        produto.setValor((double) 10);

        return produto;
    }

    private CategoriaProduto categoria(){
        CategoriaProduto categoria = new CategoriaProduto();

        categoria.setCategoria("Comida");
        categoria.setId(1L);

        return categoria;
    }

    private List<PedidoForm> criaPedidos(){
        List<PedidoForm> pedidos = new ArrayList<>();
        pedidos.add(pedidoForm());
        pedidos.add(pedidoForm());
        pedidos.add(pedidoForm());
        pedidos.add(pedidoForm());

        return pedidos;
    }
}