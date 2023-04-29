package com.rasmoo.dao;

import com.rasmoo.model.Cliente;
import com.rasmoo.model.ClienteId;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteDAO {
    private EntityManager entityManager;

    public ClienteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Cliente> findByName(final String name) {
        String jpql = "SELECT c FROM Cliente c WHERE LOWER(c.nome) LIKE LOWER(:name)";
        return this.entityManager.createQuery(jpql, Cliente.class).setParameter("name", "%" + name + "%").getResultList();

    }

    public List<Cliente> findAll() {
        String jpql = "SELECT c FROM Cliente c";
        return this.entityManager.createQuery(jpql, Cliente.class).getResultList();
    }

    public void create(Cliente cliente) {
        this.entityManager.persist(cliente);
    }

    public Cliente findById(final ClienteId id) {
        return this.entityManager.find(Cliente.class, id);
    }

    public void update(final Cliente cliente) {
        this.entityManager.merge(cliente);
    }

    public void delete(final Cliente cliente) {
        this.entityManager.remove(cliente);
    }
}
