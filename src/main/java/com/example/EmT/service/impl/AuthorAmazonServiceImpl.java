package com.example.EmT.service.impl;

import com.example.EmT.model.Author;
import com.example.EmT.service.AuthorService;
import com.example.EmT.service.AuthorService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("amazon")
public class  AuthorAmazonServiceImpl implements AuthorService {
    @Override
    public List<Author> findAll() {
        return null;
    }

    @Override
    public List<Author> findAllByName(String name) {
        return null;
    }

    @Override
    public Author findById(Long id) {
        return null;
    }

    @Override
    public Author save(Author author) {
        return null;
    }

    @Override
    public Author update(Long id, Author author) {
        return null;
    }

    @Override
    public Author updateName(Long id, String name) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
