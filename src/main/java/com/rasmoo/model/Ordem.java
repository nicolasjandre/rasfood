package com.rasmoo.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ordem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "valor_total")
    private Double valorTotal;

    @Column(name = "data_de_criacao")
    private LocalDateTime dataDeCriacao = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @OneToMany(mappedBy = "ordem", cascade = CascadeType.ALL)
    private List<OrdemCardapio> ordemCardapioList = new ArrayList<>();

    public Ordem(Cliente cliente) {
        this.cliente = cliente;
        this.valorTotal = 0.0;
    }

    public Ordem() {
        this.valorTotal = 0.0;
    }

    public List<OrdemCardapio> getOrdemCardapioList() {
        return ordemCardapioList;
    }

    public void setOrdemCardapioList(List<OrdemCardapio> ordemCardapioList) {
        this.ordemCardapioList = ordemCardapioList;
    }

    public void addOrdemCardapio(OrdemCardapio ordemCardapio) {
        ordemCardapio.setOrdem(this);
        this.ordemCardapioList.add(ordemCardapio);
        this.valorTotal += ordemCardapio.getValor() * ordemCardapio.getQuantidade();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(LocalDateTime dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
