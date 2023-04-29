package com.rasmoo.dao;

import com.rasmoo.model.Endereco;
import com.rasmoo.vo.ClienteVo;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

public class EnderecoDAO {
    private EntityManager entityManager;

    public EnderecoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Endereco endereco) {
        this.entityManager.persist(endereco);
    }

    public List<ClienteVo> findCustomerCriteria(final String estado, final String cidade, final String rua) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ClienteVo> criteriaQuery = builder.createQuery(ClienteVo.class);
        Root<Endereco> root = criteriaQuery.from(Endereco.class);
        criteriaQuery.multiselect(root.get("cliente").get("cpf"), root.get("cliente").get("nome"));
        Predicate predicate = builder.and();

        if (Objects.nonNull(estado)) {
            predicate = builder.and(predicate, builder.equal(builder.lower(root.get("uf")), estado.toLowerCase()));
        }
        if (Objects.nonNull(cidade)) {
            predicate = builder.and(predicate, builder.equal(builder.lower(root.get("cidade")), cidade.toLowerCase()));
        }
        if (Objects.nonNull(rua)) {
            predicate = builder.and(predicate, builder.equal(builder.lower(root.get("rua")), rua.toLowerCase()));
        }

        criteriaQuery.where(predicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }


    public List<ClienteVo> findCustomer(final String estado, final String cidade, final String rua) {
        String jpql = "SELECT new com.rasmoo.vo.ClienteVo(e.cliente.clienteId.cpf, e.cliente.nome) " +
                "FROM Endereco e WHERE 1=1 ";
        if (Objects.nonNull(estado)) {
            jpql = jpql.concat("AND LOWER(e.uf) = LOWER(:estado) ");
        }
        if (Objects.nonNull(cidade)) {
            jpql = jpql.concat("AND LOWER(e.cidade) = LOWER(:cidade) ");

        }
        if (Objects.nonNull(rua)) {
            jpql = jpql.concat("AND LOWER(e.rua) = LOWER(:rua) ");
        }

        TypedQuery typedQuery = this.entityManager.createQuery(jpql, ClienteVo.class);

        if (Objects.nonNull(estado)) {
            typedQuery.setParameter("estado", estado);
        }
        if (Objects.nonNull(cidade)) {
            typedQuery.setParameter("cidade", cidade);
        }
        if (Objects.nonNull(rua)) {
            typedQuery.setParameter("rua", rua);
        }

        return typedQuery.getResultList();
    }

    public Endereco findById(final Integer id) {
        return this.entityManager.find(Endereco.class, id);
    }


    public void update(final Endereco endereco) {
        this.entityManager.merge(endereco);
    }

    public void delete(final Endereco endereco) {
        this.entityManager.remove(endereco);
    }
}
