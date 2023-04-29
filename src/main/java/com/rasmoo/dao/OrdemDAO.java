package com.rasmoo.dao;

import com.rasmoo.model.Ordem;
import com.rasmoo.vo.ItensPrincipaisVo;

import javax.persistence.EntityManager;
import java.util.List;

public class OrdemDAO {
    private EntityManager entityManager;

    public OrdemDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Ordem> findAll() {
        String jpql = "SELECT o FROM Ordem o";
        return this.entityManager.createQuery(jpql, Ordem.class).getResultList();
    }

    public Ordem joinFetchCliente(final Integer id) {
        String jpql = "SELECT o FROM Ordem o JOIN FETCH o.cliente WHERE o.id = id";
        return this.entityManager.createQuery(jpql, Ordem.class).setParameter("id", id).getSingleResult();
    }

    public List<ItensPrincipaisVo> findTopSellers() {
        String jpql = "SELECT new com.rasmoo.vo.ItensPrincipaisVo(c.nome, SUM(oc.quantidade)) FROM Ordem o " +
                "JOIN OrdemCardapio oc on o.id = oc.cardapio.id " +
                "JOIN oc.cardapio c " +
                "GROUP BY c.nome " +
                "ORDER BY SUM(oc.quantidade) DESC"; //esta retornando somente um item
        return this.entityManager.createQuery(jpql, ItensPrincipaisVo.class).getResultList();
    }

    public void create(Ordem ordem) {
        this.entityManager.persist(ordem);
    }

    public Ordem findById(final Integer id) {
        return this.entityManager.find(Ordem.class, id);
    }

    public void update(final Ordem ordem) {
        this.entityManager.merge(ordem);
    }

    public void delete(final Ordem ordem) {
        this.entityManager.remove(ordem);
    }
}