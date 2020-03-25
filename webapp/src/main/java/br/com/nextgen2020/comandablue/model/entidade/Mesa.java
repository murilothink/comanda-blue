package br.com.nextgen2020.comandablue.model.entidade;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//Abrir discussao com o Luciano e os estags sobre a ideia dos PINS, QR CODE, Id da Mesa
@Entity
public class Mesa {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    @Id
    private Integer id;

    @NotNull
    private String nome;

    //Disscutir se pin deve ser vinculado ao estabelecimento ou a mesa
    @NotEmpty
    private String pin;

    @NotEmpty
    private String qrCode;
}
