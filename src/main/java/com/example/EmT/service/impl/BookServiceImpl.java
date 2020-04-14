package com.example.EmT.service.impl;

import com.example.EmT.model.Author;
import com.example.EmT.model.Book;
import com.example.EmT.model.exception.BookNotFoundException;
import com.example.EmT.repository.BookRepository;
import com.example.EmT.service.AuthorService;
import com.example.EmT.service.BookService;
import com.example.EmT.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository,
        AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public List<Book> findAllByAuthorId(Long authorId) {
        return this.bookRepository.findAllByAuthorId(authorId);
    }

    @Override
    public List<Book> findAllByCategoryId(Long categoryId){
        return this.bookRepository.findAllByCategoryId(categoryId);
    }
    @Override
    public List<Book> findAllSortedByPrice(boolean asc) {
        return this.bookRepository.findAllSortedByPrice(asc);
    }

    @Override
    public Book findById(Long id) {
        return this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public Book saveBook(String name, Float price, Integer quantity, Long AuthorId, Long CategoryId)
        throws IOException {
        return null;
    }


    @Override
    public Book saveBook(Book book, MultipartFile image) throws IOException {
        Author author = this.authorService.findById(book.getAuthor().getId());
        book.setAuthor(author);
        if (image != null && !image.getName().isEmpty()) {
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
            book.setImageBase64(base64Image);
        }
        return this.bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book, MultipartFile image) throws IOException {
        Book p = this.findById(id);
        Author author = this.authorService.findById(book.getAuthor().getId());
        p.setAuthor(author);
        p.setPrice(book.getPrice());
        if (image != null && !image.getName().isEmpty()) {
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
            p.setImageBase64(base64Image);
        }
        return this.bookRepository.save(p);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }
}
