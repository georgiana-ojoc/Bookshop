package com.springbootdata.bookshop.repository;

import com.springbootdata.bookshop.utility.EntityManagerSingleton;

import javax.persistence.EntityManager;

public abstract class AbstractRepository {
    protected final EntityManager entityManager;

    protected AbstractRepository() {
        entityManager = EntityManagerSingleton.getInstance().getEntityManager();
    }
}
