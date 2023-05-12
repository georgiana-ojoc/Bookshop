package boo.springbootdata.bookshop;

import boo.springbootdata.bookshop.model.domain.Book;
import boo.springbootdata.bookshop.repository.BookJpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

public class JpaExample {
    public static void main(String[] arguments) {
        BookJpaRepository bookRepository = new BookJpaRepository();
        Book book = Book.builder()
                .title("Dune")
                .numberOfPages(450)
                .price(BigDecimal.valueOf(65))
                .dateOfPublishing(LocalDate.of(2000, 4, 10))
                .build();
        book = bookRepository.create(book);
        System.out.println("New book: " + book);

        book = bookRepository.findById(1);
        System.out.println("Book with ID = 1: " + book);

        book = bookRepository.updatePrice(1, BigDecimal.valueOf(70));
        System.out.println("Updated book: " + book);
    }
}
