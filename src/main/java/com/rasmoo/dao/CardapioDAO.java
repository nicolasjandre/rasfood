package com.rasmoo.dao;

import com.rasmoo.model.Cardapio;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

public class CardapioDAO {
    private EntityManager entityManager;

    public CardapioDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Cardapio cardapio) {
        this.entityManager.persist(cardapio);
    }

    public Cardapio findById(final Integer id) {
        try {
            return this.entityManager.find(Cardapio.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Cardapio> findAll() {
        try {
            String jpql = "SELECT c FROM Cardapio c";
            return this.entityManager.createQuery(jpql, Cardapio.class).getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public List<Cardapio> findByPrice() {
        try {
            String jpql = "SELECT c FROM Cardapio c ORDER BY c.valor";
            return this.entityManager.createQuery(jpql, Cardapio.class).getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public void update(final Cardapio cardapio) {
        this.entityManager.merge(cardapio);
    }

    public void delete(final Cardapio cardapio) {
        this.entityManager.remove(cardapio);
    }
}
