package br.com.nextgen2020.comandablue;

import br.com.nextgen2020.comandablue.model.User;
import br.com.nextgen2020.comandablue.repository.UserRepository;
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
	private UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void whenFindByName_thenReturnUser() {
		// given
		User zeze = new User("zeze.foca@yahoo.com", "José Foca da Silva", "12345");
		entityManager.persist(zeze);
		entityManager.flush();

		// when
		User found = userRepository.findByName(zeze.getName());

		// then
		assertThat(found.getName()).isEqualTo(zeze.getName());
	}

}
