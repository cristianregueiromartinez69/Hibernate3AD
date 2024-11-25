package service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import models.Pokedex;

import java.util.List;

public class Crud {

    public void insertPokemons(List<Pokedex> pokedexDTOList){
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager em = managerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try(managerFactory; em){
            tx.begin();

            for(Pokedex pokedex : pokedexDTOList){
                em.persist(pokedex);
            }
            tx.commit();
        }finally {
            if(tx.isActive()){
                tx.rollback();
            }
        }
    }

    public void readPokemons(){
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager em = managerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<Pokedex> pokedexList = null;
        try(managerFactory; em){
            tx.begin();
            pokedexList = em.createQuery("select p from Pokedex p", Pokedex.class).getResultList();
            for(Pokedex pokedex : pokedexList){
                System.out.println(pokedex);
            }
            tx.commit();
        }finally {
            if(tx.isActive()){
                tx.rollback();
            }
        }
    }
}
