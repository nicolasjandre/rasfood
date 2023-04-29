package com.rasmoo.dao;

import com.rasmoo.model.Categoria;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoriaDAO {
    private EntityManager entityManager;

    public CategoriaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Categoria categoria) {
        this.entityManager.persist(categoria);
    }

    public List<Categoria> findAll() {
        String jpql = "SELECT c FROM Categoria c";
        return this.entityManager.createQuery(jpql, Categoria.class).getResultList();
    }

    public Categoria findById(final Integer id) {
        return this.entityManager.find(Categoria.class, id);
    }

    public void update(final Categoria categoria) {
        this.entityManager.merge(categoria);

    }

    public void delete(final Categoria categoria) {
        this.entityManager.remove(categoria);
    }
}
