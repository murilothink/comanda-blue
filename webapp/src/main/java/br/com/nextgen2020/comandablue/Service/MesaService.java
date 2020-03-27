package br.com.nextgen2020.comandablue.Service;

import br.com.nextgen2020.comandablue.model.entidade.Mesa;
import br.com.nextgen2020.comandablue.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    public boolean verificaPin(String pin) {
        Mesa mesa = mesaRepository.findByPin(pin);

        if(mesa!=null){
            return true;
        }

        return false;
    }
}
