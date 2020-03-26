package br.com.nextgen2020.comandablue;

import br.com.nextgen2020.comandablue.model.entidade.*;
import br.com.nextgen2020.comandablue.repository.CategoriaProdutoRepository;
import br.com.nextgen2020.comandablue.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class ComandablueApplicationTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CategoriaProdutoRepository categoriaProdutoRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void whenFindByNome_thenReturnUsuario() {
		// given
		Usuario zeze = new Usuario("José Foca da Silva", "zeze.foca@yahoo.com", "12345");
		entityManager.persist(zeze);
		entityManager.flush();

		// when
		Usuario found = usuarioRepository.findByNome(zeze.getNome());

		// then
		assertThat(found.getNome()).isEqualTo(zeze.getNome());
	}

	@Test
	public void whenFindByCategoriaAndEstabelecimento_thenReturnCategoria() {

		// given
		Estabelecimento barDoZe = new Estabelecimento("12345678901234", "Bar do zé",
				"Rua da Santa Missa, 840, Centro, Leme/SP",
				"Desde 1978 servindo cerveja gelada e o melhor torresmo de Leme");
		entityManager.persist(barDoZe);
		entityManager.flush();

		CategoriaProduto catSalgado = new CategoriaProduto(barDoZe, "Salgado");
		entityManager.persist(catSalgado);
		entityManager.flush();

		// when
		CategoriaProduto found = categoriaProdutoRepository.findByCategoriaAndEstabelecimento(
				"Salgado", barDoZe);

		// then
		assertThat(found.getCategoria()).isEqualTo(catSalgado.getCategoria());
	}

}
