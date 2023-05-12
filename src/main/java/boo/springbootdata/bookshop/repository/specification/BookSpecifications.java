package boo.springbootdata.bookshop.repository.specification;

import boo.springbootdata.bookshop.model.domain.Book;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class BookSpecifications {
    private BookSpecifications() {

    }

    public static Specification<Book> bookHasTitleContainingIgnoreCaseAndPriceLessThanEqual(String title,
                                                                                            BigDecimal price) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("title").as(String.class)),
                                String.format("%%%s%%", title.toUpperCase())),
                        criteriaBuilder.lessThanOrEqualTo(root.get("price").as(BigDecimal.class), price));
    }
}
