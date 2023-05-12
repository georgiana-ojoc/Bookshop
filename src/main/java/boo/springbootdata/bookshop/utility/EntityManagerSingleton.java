package boo.springbootdata.bookshop.utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Closeable;

public class EntityManagerSingleton implements Closeable {
    private static EntityManagerSingleton instance = null;
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    private EntityManagerSingleton() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Bookshop");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static EntityManagerSingleton getInstance() {
        if (instance == null) {
            instance = new EntityManagerSingleton();
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void close() {
        if (instance != null) {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
