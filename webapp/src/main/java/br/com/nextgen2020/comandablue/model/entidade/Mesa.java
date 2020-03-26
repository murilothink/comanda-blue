package br.com.nextgen2020.comandablue.model.entidade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // varias mesas para 1 estabelecimento
    private Estabelecimento estabelecimento;

    @NotEmpty
    private String nome;

    // TODO Gerar pin e qrcode -> Por eqto usar idEstabelecimento-idMesa
    private String pin;

    private String qrCode;

    public Mesa(){
        // Necessita de construtor para metodos embutidos do MesaRepository
    }

    public Mesa(Estabelecimento estabelecimento,
                @NotEmpty String nome) {
        this.estabelecimento = estabelecimento;
        this.nome = nome;
        this.pin="";
        this.qrCode="";
    }

    public Long getId() {
        return id;
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

    public void setPin() {
        this.pin = String.valueOf(estabelecimento.getId()) + "-" + String.valueOf(this.getId());
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }
}
