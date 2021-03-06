package com.example.EmT.service.impl;

import com.example.EmT.model.Author;
import com.example.EmT.model.exception.AuthorNotFoundException;
import com.example.EmT.repository.AuthorRepository;
import com.example.EmT.repository.AuthorRepository;
import com.example.EmT.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("!amazon")
public class AuthorServiceImpl implements AuthorService {


    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public List<Author> findAllByName(String name) {
//        return this.authorRepository.findAllByName(name);
        return null;
    }

    @Override
    public Author findById(Long id) {
        return this.authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }

    @Override
    public Author save(Author author) {
        return this.authorRepository.save(author);
    }

    @Override
    public Author update(Long id, Author author) {
        Author m = this.findById(id);
        m.setName(author.getName());
//        m.setAddress(author.setAddress())
        return this.authorRepository.save(m);
    }

    @Override
    public Author updateName(Long id, String name) {
        Author m = this.findById(id);
        m.setName(name);
        return this.authorRepository.save(m);
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
