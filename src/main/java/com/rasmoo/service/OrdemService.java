package com.rasmoo.service;

import com.rasmoo.dao.CardapioDAO;
import com.rasmoo.dao.ClienteDAO;
import com.rasmoo.dao.EnderecoDAO;
import com.rasmoo.dao.OrdemDAO;
import com.rasmoo.model.Cardapio;
import com.rasmoo.util.CreateStandardData;
import com.rasmoo.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class OrdemService {
    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        var cardapioDao = new CardapioDAO(entityManager);
        var ordemDao = new OrdemDAO(entityManager);
        var enderecoDao = new EnderecoDAO(entityManager);
        var clienteDao = new ClienteDAO(entityManager);
        entityManager.getTransaction().begin();

        List<Cardapio> cardapios = cardapioDao.findAll();

        if (cardapios.size() < 1) {
            CreateStandardData.createCategorias(entityManager);
            CreateStandardData.createCardapios(entityManager);
            CreateStandardData.createClientes(entityManager);
            CreateStandardData.createOrdemCliente(entityManager);
        }

        System.out.println(ordemDao.findTopSellers());
        System.out.println(clienteDao.findByName("denise"));
        System.out.println(enderecoDao.findCustomer("rj", null, null));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}