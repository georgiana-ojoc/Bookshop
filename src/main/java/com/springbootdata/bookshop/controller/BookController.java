package com.springbootdata.bookshop.controller;

import com.springbootdata.bookshop.model.domain.Book;
import com.springbootdata.bookshop.model.dto.BookDTO;
import com.springbootdata.bookshop.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("books")
@AllArgsConstructor
public class BookController {
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.create(bookDTO), CREATED);
    }

    // region GET

    @GetMapping
    public ResponseEntity<List<Book>> get(@RequestParam(name = "title", required = false) String title,
                                          @RequestParam(name = "price-less-than-or-equal", required = false)
                                                  BigDecimal price,
                                          @RequestParam(name = "date-of-publishing-before", required = false)
                                          @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate dateOfPublishingBefore,
                                          @RequestParam(name = "date-of-publishing", required = false)
                                          @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate dateOfPublishing,
                                          @RequestParam(name = "page", required = false) Integer page,
                                          @RequestParam(name = "size", required = false) Integer size) {
        return ResponseEntity.ok(bookService.get(title, price, dateOfPublishingBefore, dateOfPublishing, page, size));
    }

    @GetMapping(value = "even-number-of-pages")
    public ResponseEntity<List<Book>> getWithEvenNumberOfPages() {
        return ResponseEntity.ok(bookService.getWithEvenNumberOfPages());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Book> getById(@PathVariable Integer id) {
        Book book = bookService.getById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    // endregion

    @PutMapping(value = "{id}")
    public ResponseEntity<Book> update(@PathVariable Integer id, @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.update(id, bookDTO, false));
    }

    @PatchMapping(value = "{id}")
    public ResponseEntity<Book> partialUpdate(@PathVariable Integer id, @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.update(id, bookDTO, true));
    }

    // region DELETE

    @DeleteMapping(value = "odd-number-of-pages")
    public ResponseEntity<String> deleteWithOddNumberOfPages() {
        Integer numberOfDeletedBooks = bookService.deleteWithOddNumberOfPages();
        return ResponseEntity.ok(String.format("%d books have been deleted successfully", numberOfDeletedBooks));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (!bookService.deleteById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(String.format("The book: %d has been deleted successfully.", id));
    }

    // endregion
}
