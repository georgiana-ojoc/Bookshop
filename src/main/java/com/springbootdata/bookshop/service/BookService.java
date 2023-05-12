package com.springbootdata.bookshop.service;

import com.springbootdata.bookshop.model.domain.Book;
import com.springbootdata.bookshop.model.dto.BookDTO;
import com.springbootdata.bookshop.repository.BookRepository;
import com.springbootdata.bookshop.repository.specification.BookSpecifications;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private ModelMapper modelMapper;
    private BookRepository bookRepository;

    public Book create(BookDTO bookDTO) {
        return bookRepository.save(modelMapper.map(bookDTO, Book.class));
    }

    // region READ

    public List<Book> get(String title, BigDecimal price, LocalDate dateOfPublishingBefore,
                          LocalDate dateOfPublishing, Integer page, Integer size) {
        if (title != null && !title.isEmpty()) {
            if (price != null) {
//                return bookRepository.findByTitleContainingIgnoreCaseAndPriceLessThanEqual(title, price);
                return bookRepository.findAll(BookSpecifications
                        .bookHasTitleContainingIgnoreCaseAndPriceLessThanEqual(title, price));
            } else {
                return bookRepository.findByTitleContainingIgnoreCase(title);
            }
        } else {
            if (price != null) {
                return bookRepository.findByPriceLessThanEqual(price);
            }
        }
        if (dateOfPublishingBefore != null) {
            return bookRepository.findByDateOfPublishingBefore(dateOfPublishingBefore);
        }
        if (dateOfPublishing != null) {
            return bookRepository.findByDateOfPublishing(dateOfPublishing,
                    PageRequest.of(page, size, Sort.by("price").descending()));
        }
        return bookRepository.findAll();
    }

    public List<Book> getWithEvenNumberOfPages() {
        return bookRepository.findWithEvenNumberOfPages();
    }

    public Book getById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    // endregion

    public Book update(Integer id, BookDTO bookDTO, boolean skipNull) {
        modelMapper.getConfiguration().setSkipNullEnabled(skipNull);
        Optional<Book> optionalBook = bookRepository.findById(id);
        Book book;
        if (optionalBook.isPresent()) {
            book = optionalBook.get();
            modelMapper.map(bookDTO, book);
        } else {
            book = modelMapper.map(bookDTO, Book.class);
        }
        return bookRepository.save(book);
    }

    // region DELETE

    @Transactional
    public Integer deleteWithOddNumberOfPages() {
        return bookRepository.deleteWithOddNumberOfPages();
    }

    public boolean deleteById(Integer id) {
        if (!bookRepository.existsById(id)) {
            return false;
        }
        bookRepository.deleteById(id);
        return true;
    }

    // endregion
}
