package com.rasmoo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente {

    @EmbeddedId
    private ClienteId clienteId;

    private String nome;

    @Embedded
    private Contato contato;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();
    public Cliente(String cpf, String email, String nome) {
        this.clienteId = new ClienteId(email, cpf);
        this.nome = nome;
    }

    public String getDdd() {
        return this.contato.getDdd();
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Contato getContato() {
        return contato;
    }

    public String getNumero() {
        return this.contato.getNumero();
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Cliente() {

    }

    public void addEndereco(Endereco endereco) {
        endereco.setCliente(this);
        this.enderecos.add(endereco);
    }

    public String getCpf() {
        return clienteId.getCpf();
    }

    public void setCpf(String cpf) {
        this.clienteId.setCpf(cpf);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ClienteId getClienteId() {
        return clienteId;
    }

    public void setClienteId(ClienteId clienteId) {
        this.clienteId = clienteId;
    }

    public String getEmail() {
        return this.clienteId.getEmail();
    }

    public void setEmail(String email) {
        this.clienteId.setEmail(email);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "email='" + clienteId.getEmail() + '\'' +
                "cpf='" + clienteId.getCpf() + '\'' +
                ", nome='" + nome + '\'' +
                ", enderecos=" + enderecos +
                '}';
    }
}
