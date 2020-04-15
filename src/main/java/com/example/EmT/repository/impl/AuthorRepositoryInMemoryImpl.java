package com.example.EmT.repository.impl;

import com.example.EmT.model.Author;
import com.example.EmT.repository.AuthorRepository;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Profile("in-memory")
public class AuthorRepositoryInMemoryImpl implements AuthorRepository {

    private HashMap<Long, Author> authors;
    private AtomicLong counter;

    @PostConstruct
    public void init() {
        this.authors = new HashMap<>();
        this.counter = new AtomicLong(0);
        Author m1 = new Author(1L, "J. K. Rowling");
        Author m2 = new Author(2L, "J. R. R. Tolkien");
        this.authors.put(m1.getId(), m1);
        this.authors.put(m2.getId(), m2);

    }


    @Override
    public List<Author> findAll() {
        return new ArrayList<>(this.authors.values());
    }

    @Override
    public List<Author> findAllByName(String name) {
        return null;
    }

    @Override
    public List<Author> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Author> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Author> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public <S extends Author> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Author> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Author> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Author getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Author> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Author> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Author> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Author> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Author> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Author> boolean exists(Example<S> example) {
        return false;
    }

//    @Override
//    public List<Author> findAllByName(String name) {
//        return this.authors.values()
//                .stream()
//                .filter(item -> item.getName().contains(name))
//                .collect(Collectors.toList());
//    }

    @Override
    public Optional<Author> findById(Long id) {
        return Optional.ofNullable(this.authors.get(id));
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Author save(Author author) {
        if (author.getId() == null) {
            author.setId(this.counter.getAndIncrement());
        }
        this.authors.put(author.getId(), author);
        return author;
    }

    @Override
    public void deleteById(Long id) {
        this.authors.remove(id);
    }

    @Override
    public void delete(Author author) {

    }

    @Override
    public void deleteAll(Iterable<? extends Author> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
