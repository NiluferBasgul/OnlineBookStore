package com.example.EmT.repository.impl;

import com.example.EmT.model.Book;
import com.example.EmT.repository.BookRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private HashMap<Long, Book> books;
    private Long counter;



    @PostConstruct
    public void init() {
        this.counter = 0L;
        this.books = new HashMap<>();
        Long id = this.generateKey();
        Long numberOfBooks = this.generateKey();
        this.books.put(id, new Book(id,"Harry Potter",numberOfBooks,1f,null,null,null));
        id = this.generateKey();
        numberOfBooks = this.generateKey();
        this.books.put(id, new Book(id,"Lord of the Rings",numberOfBooks, 1f,null,null,null));
    }


    @Override
    public List<Book> findAll() {
        return new ArrayList<>(this.books.values());
    }

    @Override
    public List<Book> findAllSortedByPrice(boolean asc) {
        Comparator<Book> priceComparator = Comparator.comparing(Book::getPrice);
        if (!asc) {
            priceComparator = priceComparator.reversed();
        }
        return this.books.values()
                .stream()
                .sorted(priceComparator)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findAllByAuthorId(Long authorId) {
        return this.books.values()
                .stream()
                .filter(item -> item.getAuthor().getId().equals(authorId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findAllByCategoryId(Long categoryId) {
        return this.books.values()
            .stream()
            .filter(item -> item.getCategory().getId().equals(categoryId))
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(this.books.get(id));
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(this.generateKey());
        }
        this.books.put(book.getId(), book);
        return book;
    }

    @Override
    public void deleteById(Long id) {
        this.books.remove(id);
    }

    private Long generateKey() {
        return ++counter;
    }
}
