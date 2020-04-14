package com.example.EmT.repository;

import com.example.EmT.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> findAll();
    List<Book> findAllSortedByPrice(boolean asc);
    List<Book> findAllByAuthorId(Long authorId);
    List<Book> findAllByCategoryId(Long categoryId);
    Optional<Book> findById(Long id);
    Book save(Book book);
    void deleteById(Long id);
}
