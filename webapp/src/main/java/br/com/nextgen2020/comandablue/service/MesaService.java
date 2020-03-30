package br.com.nextgen2020.comandablue.service;

import br.com.nextgen2020.comandablue.model.entidade.Mesa;
import br.com.nextgen2020.comandablue.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    /**
     * Faz a verificacao do pin da mesa
     * @param pin Pin da mesa, composto por {idEstabelecimento}-{idMesa}
     * @return true se OK, false se falhou
     */
    public boolean verificaPin(String pin) {
        Mesa mesa = mesaRepository.findByPin(pin);

        if(mesa!=null){
            return true;
        }

        return false;
    }
}
