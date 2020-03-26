package br.com.nextgen2020.comandablue;

import javax.persistence.EntityManager;

import br.com.nextgen2020.comandablue.model.entidade.CategoriaProduto;
import br.com.nextgen2020.comandablue.model.entidade.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import br.com.nextgen2020.comandablue.model.entidade.Usuario;

@Configuration
public class RestConfigurerAdapter extends RepositoryRestConfigurerAdapter {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Usuario.class);
        config.exposeIdsFor(CategoriaProduto.class);
        config.exposeIdsFor(Produto.class);
    }
}