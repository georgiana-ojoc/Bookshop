package boo.springbootdata.bookshop.controller;

import boo.springbootdata.bookshop.model.domain.Author;
import boo.springbootdata.bookshop.model.dto.AuthorDTO;
import boo.springbootdata.bookshop.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("authors")
@AllArgsConstructor
public class AuthorController {
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<Author> create(@RequestBody AuthorDTO authorDTO) {
        return new ResponseEntity<>(authorService.create(authorDTO), CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Author>> get() {
        return ResponseEntity.ok(authorService.get());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Integer id) {
        Author author = authorService.getById(id);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(author);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Author> update(@PathVariable Integer id, @RequestBody AuthorDTO authorDTO) {
        return ResponseEntity.ok(authorService.update(id, authorDTO, false));
    }

    @PatchMapping(value = "{id}")
    public ResponseEntity<Author> partialUpdate(@PathVariable Integer id, @RequestBody AuthorDTO authorDTO) {
        return ResponseEntity.ok(authorService.update(id, authorDTO, true));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Integer> deleteAuthor(@PathVariable Integer id) {
        if (!authorService.deleteById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(id);
    }
}
