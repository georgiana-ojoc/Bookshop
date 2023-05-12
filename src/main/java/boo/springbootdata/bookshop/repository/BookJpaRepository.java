package boo.springbootdata.bookshop.repository;

import boo.springbootdata.bookshop.model.domain.Book;

import java.math.BigDecimal;

public class BookJpaRepository extends AbstractRepository {
    public Book create(Book book) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(book);
            entityManager.getTransaction().commit();
            return book;
        } catch (Exception exception) {
            return null;
        }
    }

    public Book findById(Integer id) {
        return entityManager.find(Book.class, id);
    }

    public Book updatePrice(Integer id, BigDecimal price) {
        Book book = entityManager.find(Book.class, id);
        try {
            entityManager.getTransaction().begin();
            book.setPrice(price);
            entityManager.getTransaction().commit();
            return book;
        } catch (Exception exception) {
            return null;
        }
    }
}
