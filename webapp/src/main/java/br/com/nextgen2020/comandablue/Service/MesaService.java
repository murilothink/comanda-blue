package br.com.nextgen2020.comandablue.Service;

import br.com.nextgen2020.comandablue.model.entidade.Mesa;
import br.com.nextgen2020.comandablue.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    public boolean verificaPIN(String PIN) {
        Mesa mesa = mesaRepository.findByPIN(PIN);
        if(mesa!=null){
            if(mesa.getPin().equals(PIN)){
                return true;
            }
        }
        return false;
    }
}
