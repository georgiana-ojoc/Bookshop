package boo.springbootdata.bookshop.repository;

import boo.springbootdata.bookshop.utility.EntityManagerSingleton;

import javax.persistence.EntityManager;

public abstract class AbstractRepository {
    protected final EntityManager entityManager;

    protected AbstractRepository() {
        entityManager = EntityManagerSingleton.getInstance().getEntityManager();
    }
}
