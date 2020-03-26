package br.com.nextgen2020.comandablue;


import br.com.nextgen2020.comandablue.model.entidade.*;
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

	@Test
	void contextLoads() {
	}

	@Test
	public void whenFindByName_thenReturnUsuario() {
		// given
		Usuario zeze = new Usuario("zeze.foca@yahoo.com", "José Foca da Silva", "12345");
		entityManager.persist(zeze);
		entityManager.flush();

		// when
		Usuario found = usuarioRepository.findByName(zeze.getNome());

		// then
		assertThat(found.getNome()).isEqualTo(zeze.getNome());
	}


}
