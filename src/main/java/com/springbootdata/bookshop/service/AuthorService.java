package com.springbootdata.bookshop.service;

import com.springbootdata.bookshop.model.domain.Author;
import com.springbootdata.bookshop.model.dto.AuthorDTO;
import com.springbootdata.bookshop.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorService {
    private ModelMapper modelMapper;
    private AuthorRepository authorRepository;

    public Author create(AuthorDTO authorDTO) {
        return authorRepository.save(modelMapper.map(authorDTO, Author.class));
    }

    public List<Author> get() {
        return authorRepository.findAll();
    }

    public Author getById(Integer id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Author update(Integer id, AuthorDTO authorDTO, boolean skipNull) {
        modelMapper.getConfiguration().setSkipNullEnabled(skipNull);
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        Author author;
        if (optionalAuthor.isPresent()) {
            author = optionalAuthor.get();
            modelMapper.map(authorDTO, author);
        } else {
            author = modelMapper.map(authorDTO, Author.class);
        }
        return authorRepository.save(author);
    }

    public boolean deleteById(Integer id) {
        if (!authorRepository.existsById(id)) {
            return false;
        }
        authorRepository.deleteById(id);
        return true;
    }
}
