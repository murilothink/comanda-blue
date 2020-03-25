package br.com.nextgen2020.comandablue.model.entidade;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Entity;
@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Cliente cliente;

    private Comanda comanda;

    private Integer valorPago;

    private TipoPagamento tipoPagemento;

}
