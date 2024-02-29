package org.example;


import jakarta.persistence.*;

import java.util.function.Consumer;

public class HibernateMsSqlBugReproducer {
    private final EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {

        new HibernateMsSqlBugReproducer();
    }

    public HibernateMsSqlBugReproducer() {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        inTransaction(em -> {

        });
    }

    void inTransaction(Consumer<EntityManager> work) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            work.accept(entityManager);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
        finally {
            entityManager.close();
        }
    }

}