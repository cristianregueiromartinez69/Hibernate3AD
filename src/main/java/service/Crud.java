package service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import models.Pokedex;

import java.math.BigDecimal;
import java.util.List;

public class Crud {

    //--------------------------------------------Metodos con objetos-------------------------------------//

    public void insertPokemons(List<Pokedex> pokedexDTOList) {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager em = managerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try (managerFactory; em) {
            tx.begin();

            for (Pokedex pokedex : pokedexDTOList) {
                em.persist(pokedex);
            }
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
        }
    }

    public void readPokemons() {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager em = managerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<Pokedex> pokedexList = null;
        try (managerFactory; em) {
            tx.begin();
            pokedexList = em.createQuery("select p from Pokedex p", Pokedex.class).getResultList();
            for (Pokedex pokedex : pokedexList) {
                System.out.println(pokedex);
            }
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
        }
    }

    public void updatePokemons(int id, String newNombre, BigDecimal newPeso, String newMisc) {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager em = managerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try (managerFactory; em) {
            tx.begin();
            Pokedex pokedex = em.find(Pokedex.class, id);

            if (pokedex != null) {
                pokedex.setNome(newNombre);
                pokedex.setPeso(newPeso);
                pokedex.setMisc(newMisc);
            }

            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
        }
    }

    public void deletePokedex() {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager em = managerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try (managerFactory; em) {
            tx.begin();
            int deletedCount = em.createQuery("DELETE FROM Pokedex").executeUpdate();

            tx.commit();
            if (deletedCount > 0) {
                System.out.println("Se han eliminado los pokemons de la pokedex");
            }
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Error al eliminar los registros");
        }
    }

    //--------------------------------------------Metodos con querys-------------------------------------//
    public void updatePokemonsCOnQuery(int id, String newNombre, BigDecimal newPeso, String newMisc) {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager em = managerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            int updatedCount = em.createQuery("UPDATE Pokedex p SET p.nome = :nome, p.peso = :peso, p.misc = :misc WHERE p.id = :id")
                    .setParameter("id", id)
                    .setParameter("nome", newNombre)
                    .setParameter("peso", newPeso)
                    .setParameter("misc", newMisc)
                    .executeUpdate();

            tx.commit();
            System.out.println(updatedCount + " registros actualizados.");
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Error al actualizar: ");
        } finally {
            em.close();
            managerFactory.close();
        }
    }
}