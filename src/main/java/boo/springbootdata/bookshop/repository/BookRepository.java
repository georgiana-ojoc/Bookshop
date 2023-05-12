package boo.springbootdata.bookshop.repository;

import boo.springbootdata.bookshop.model.domain.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {
    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByPriceLessThanEqual(BigDecimal price);

    List<Book> findByTitleContainingIgnoreCaseAndPriceLessThanEqual(String title, BigDecimal price);

    List<Book> findByDateOfPublishingBefore(LocalDate dateOfPublishing);

    @Query(value = "SELECT b FROM books b WHERE MOD(b.numberOfPages, 2) = 0")
    List<Book> findWithEvenNumberOfPages();

    @Modifying
    @Query(value = "DELETE FROM books WHERE MOD(number_of_pages, 2) = 1", nativeQuery = true)
    Integer deleteWithOddNumberOfPages();

    List<Book> findByDateOfPublishing(LocalDate dateOfPublishing, Pageable pageable);
}
