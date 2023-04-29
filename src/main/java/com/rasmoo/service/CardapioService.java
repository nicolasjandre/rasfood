package com.rasmoo.service;

import com.rasmoo.dao.CardapioDAO;
import com.rasmoo.model.Cardapio;
import com.rasmoo.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class CardapioService {
    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();

        var cardapioDAO = new CardapioDAO(entityManager);

//        CreateStandardData.createCardapioFoods(entityManager);

        List<Cardapio> cardapios = cardapioDAO.findByPrice();

        cardapios.forEach(cardapio -> System.out.println("Cardapio: " + cardapio.getNome() + "\tVALOR: " + cardapio.getValor()));

     }
}
