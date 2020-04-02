package br.com.nextgen2020.comandablue.service;

import br.com.nextgen2020.comandablue.form.CategoriasForm;
import br.com.nextgen2020.comandablue.model.entidade.CategoriaProduto;
import br.com.nextgen2020.comandablue.model.entidade.Estabelecimento;
import br.com.nextgen2020.comandablue.repository.CategoriaProdutoRepository;
import br.com.nextgen2020.comandablue.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaProdutoService {

    @Autowired
    private CategoriaProdutoRepository categoriaProdutoRepository;

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    public CategoriasForm listarCategorias(Long idEstabelecimento) {
        Optional<Estabelecimento> estabelecimento = estabelecimentoRepository.findById(idEstabelecimento);
        CategoriasForm categoriasForm = null;

        if(estabelecimento.isPresent()){
            List<CategoriaProduto> categorias = categoriaProdutoRepository.findByEstabelecimento(estabelecimento.get());
            categoriasForm = new CategoriasForm();
            categoriasForm.setCategorias(categorias);
            categoriasForm.setEstabelecimento(estabelecimento.get());
        }

        return categoriasForm;
    }
}
