package com.rasmoo.model;

import javax.persistence.*;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cep;

    private String rua;

    private String complemento;

    private String uf;

    private String cidade;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    public Endereco(String cep, String rua, String complemento, String cidade, String uf) {
        this.cep = cep;
        this.rua = rua;
        this.complemento = complemento;
        this.uf = uf;
        this.cidade = cidade;
    }

    public Endereco() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
