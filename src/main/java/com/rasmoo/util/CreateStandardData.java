package com.rasmoo.util;

import com.rasmoo.dao.*;
import com.rasmoo.model.*;

import javax.persistence.EntityManager;
import java.util.List;

public class CreateStandardData {

    public static void createCategorias(EntityManager entityManager) {
        Categoria entrada = new Categoria("Entradas");
        Categoria salada = new Categoria("Saladas");
        Categoria principal = new Categoria("Pratos Principais");

        CategoriaDAO categoriaDao = new CategoriaDAO(entityManager);

        categoriaDao.create(entrada);
        entityManager.flush();
        categoriaDao.create(salada);
        entityManager.flush();
        categoriaDao.create(principal);
        entityManager.flush();
        entityManager.clear();
    }

    public static void createCardapios(EntityManager entityManager) {
        CategoriaDAO categoriaDao = new CategoriaDAO(entityManager);
        CardapioDAO cardapioDao = new CardapioDAO(entityManager);

        List<Categoria> categorias = categoriaDao.findAll();
        Cardapio moqueca = new Cardapio("Moqueca", "Peixe branco, banana da terra, arroz e farofa",
                true, 95.00, categorias.get(2));
        Cardapio spaguetti = new Cardapio("Spaguetti", "Spagatti ao molho de parmesão e cogumelos",
                true, 68.00, categorias.get(2));
        Cardapio bife = new Cardapio("Bife", "Bife acebolado com arroz branco, farofa e batata frita",
                true, 59.00, categorias.get(2));
        Cardapio cordeiro = new Cardapio("Cordeiro", "Cordeiro com risoto de funghi",
                true, 88.00, categorias.get(2));
        Cardapio burrata = new Cardapio("Burrata", "Tomates queimados, rúcula e torrada",
                true, 15.00, categorias.get(0));
        Cardapio bruschetta = new Cardapio("Bruschetta", "Tomate, mucarela e manjericao",
                true, 20.00, categorias.get(0));
        Cardapio scappeta = new Cardapio("Scappeta", "Ragu de linguica e torradinhas",
                true, 25.00, categorias.get(0));
        Cardapio caprese = new Cardapio("Caprese", "Mini rucula e mucarela",
                true, 47.00, categorias.get(1));
        Cardapio caesar = new Cardapio("Caesar", "Salada de franco com molho ceasar",
                true, 40.00, categorias.get(1));
        Cardapio chevre = new Cardapio("Chevre", "Mix de folhas, mostarda e mel",
                true, 59.00, categorias.get(1));

        cardapioDao.create(moqueca);
        cardapioDao.create(spaguetti);
        cardapioDao.create(bife);
        cardapioDao.create(cordeiro);
        cardapioDao.create(burrata);
        cardapioDao.create(bruschetta);
        cardapioDao.create(scappeta);
        cardapioDao.create(caprese);
        cardapioDao.create(caesar);
        cardapioDao.create(chevre);
        entityManager.flush();
        entityManager.clear();
    }

    public static void createClientes(EntityManager entityManager){

        ClienteDAO clienteDao = new ClienteDAO(entityManager);
        EnderecoDAO enderecoDao = new EnderecoDAO(entityManager);

        Endereco augusta = new Endereco("000000000","augusta","casa 43","Sao Paulo","SP");
        Cliente felipe = new Cliente("12345678900","feilp2e@email.com","Felipe Ribeiro");
        felipe.addEndereco(augusta);

        Endereco rioVermelho = new Endereco("000000001","Lapa","apto 1001","Salvador","BA");
        Cliente cleber = new Cliente("111111111110","cle2ber@email.com","Cleber Machado");
        cleber.addEndereco(rioVermelho);

        Endereco leblon = new Endereco("000000002","Lapa","apto 203","Rio de Janeiro","RJ");
        Cliente calvin = new Cliente("09876543213","calvin1@email.com","Calvin Coelho");
        calvin.addEndereco(leblon);

        Endereco heitorPenteado = new Endereco("000000000","Heitor Penteado","apto 101","Santos","SP");
        Cliente tayane = new Cliente("111111111124","tay1ane@email.com","Tayane Lopes Costa");
        tayane.addEndereco(heitorPenteado);

        Endereco consolacao = new Endereco("000000000","Lapa","apto 1001","Sao Paulo","SP");
        Cliente denise = new Cliente("111111111142","denise1@email.com","Denise Costa");
        denise.addEndereco(consolacao);

        Endereco nacoesUnidas = new Endereco("000000000","NacoesUnidas","casa 27","Sao Paulo","SP");
        Cliente claudia = new Cliente("111111111342","cla1udia@email.com","Claudia Rosa");
        claudia.addEndereco(nacoesUnidas);

        enderecoDao.create(augusta);
        clienteDao.create(felipe);
        enderecoDao.create(rioVermelho);
        clienteDao.create(cleber);
        enderecoDao.create(leblon);
        clienteDao.create(calvin);
        enderecoDao.create(heitorPenteado);
        clienteDao.create(tayane);
        enderecoDao.create(consolacao);
        clienteDao.create(denise);
        enderecoDao.create(nacoesUnidas);
        clienteDao.create(claudia);

        entityManager.flush();
        entityManager.clear();
    }

    public static void createOrdemCliente(EntityManager entityManager){
        ClienteDAO clienteDao = new ClienteDAO(entityManager);
        CardapioDAO cardapio = new CardapioDAO(entityManager);
        OrdemDAO ordemDao = new OrdemDAO(entityManager);
        List<Cliente> clientes = clienteDao.findAll();
        List<Cardapio> cardapioList = cardapio.findAll();

        Ordem ordemFelipe = new Ordem(clientes.get(0));
        ordemFelipe.addOrdemCardapio(new OrdemCardapio(cardapioList.get(0),2));
        ordemFelipe.addOrdemCardapio(new OrdemCardapio(cardapioList.get(5),3));

        Ordem ordemCleber = new Ordem(clientes.get(1));
        ordemCleber.addOrdemCardapio(new OrdemCardapio(cardapioList.get(0),1));
        ordemCleber.addOrdemCardapio(new OrdemCardapio(cardapioList.get(1),2));
        ordemCleber.addOrdemCardapio(new OrdemCardapio(cardapioList.get(6),3));

        Ordem ordemCalvin = new Ordem(clientes.get(2));
        ordemCalvin.addOrdemCardapio(new OrdemCardapio(cardapioList.get(2),2));
        ordemCalvin.addOrdemCardapio(new OrdemCardapio(cardapioList.get(9),3));

        Ordem ordemTayane = new Ordem(clientes.get(3));
        ordemTayane.addOrdemCardapio(new OrdemCardapio(cardapioList.get(0),2));
        ordemTayane.addOrdemCardapio(new OrdemCardapio(cardapioList.get(2),3));

        Ordem ordemDenise = new Ordem(clientes.get(4));
        ordemDenise.addOrdemCardapio(new OrdemCardapio(cardapioList.get(4),2));
        ordemDenise.addOrdemCardapio(new OrdemCardapio(cardapioList.get(3),1));

        Ordem ordemClaudia = new Ordem(clientes.get(5));
        ordemClaudia.addOrdemCardapio(new OrdemCardapio(cardapioList.get(3),2));
        ordemClaudia.addOrdemCardapio(new OrdemCardapio(cardapioList.get(5),3));

        ordemDao.create(ordemFelipe);
        ordemDao.create(ordemCleber);
        ordemDao.create(ordemCalvin);
        ordemDao.create(ordemTayane);
        ordemDao.create(ordemDenise);
        ordemDao.create(ordemClaudia);

        entityManager.flush();
        entityManager.clear();

    }
}
