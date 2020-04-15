package com.example.EmT.web.controller;

import com.example.EmT.model.Author;
import com.example.EmT.model.Book;
import com.example.EmT.model.Category;
import com.example.EmT.service.AuthorService;
import com.example.EmT.service.BookService;
import com.example.EmT.service.CategoryService;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookService bookService;
    private AuthorService authorService;
    private CategoryService categoryService;

    public BookController(BookService bookService,
                             AuthorService authorService, CategoryService categoryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    //    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String getBookPage(Model model) {
        List<Book> books = this.bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }


    //    @RequestMapping(method = RequestMethod.GET, value = "/add-new")
    @GetMapping("/add-new")
    public String addNewBookPage(Model model) {
        List<Author> authors = this.authorService.findAll();
        List<Category> categories = this.categoryService.findAll();

        if(authors.isEmpty()) {
            Author defaultAuthor = new Author(null, "Nilufer Basgul");
            this.authorService.save(defaultAuthor);
            List<Author> authorResultList = this.authorService.findAllByName("Nilufer Basgul");
            if(authorResultList != null) {
                if(!authorResultList.isEmpty())
                authors.add(authorResultList.get(0));
            }
            else {
                authors.add(defaultAuthor);
            }
        }

        if(categories.isEmpty()) {
            Category defaultCategory = new Category(null, "General", "Default category type");
            this.categoryService.save(defaultCategory);
            List<Category> categoryList = this.categoryService.findAll();
            if(categoryList != null) {
                if(!categoryList.isEmpty())
                    categories.add(categoryList.get(0));
            }
            else {
                categories.add(defaultCategory);
            }
        }

        model.addAttribute("authors", authors);
        model.addAttribute("categories", categories);

        model.addAttribute("book", new Book(null,"", 1L,0f,null,null,null));
        return "add-book";
    }

    @GetMapping("/{id}/edit")
    public String editBookPage(Model model, @PathVariable Long id) {
        try {
            List<Author> authors = this.authorService.findAll();
            List<Category> categories = this.categoryService.findAll();

            if(authors.isEmpty()) {
                Author defaultAuthor = new Author(null, "Nilufer Basgul");
                this.authorService.save(defaultAuthor);
                List<Author> authorResultList = this.authorService.findAllByName("Nilufer Basgul");
                if(authorResultList != null) {
                    if(!authorResultList.isEmpty())
                        authors.add(authorResultList.get(0));
                }
                else {
                    authors.add(defaultAuthor);
                }
            }

            if(categories.isEmpty()) {
                Category defaultCategory = new Category(null, "General", "Default category type");
                this.categoryService.save(defaultCategory);
                List<Category> categoryList = this.categoryService.findAll();
                if(categoryList != null) {
                    if(!categoryList.isEmpty())
                        categories.add(categoryList.get(0));
                }
                else {
                    categories.add(defaultCategory);
                }
            }

            model.addAttribute("authors", authors);
            model.addAttribute("categories", categories);


            Book book = this.bookService.findById(id);
            model.addAttribute("book", book);
            return "add-book";
        } catch (RuntimeException ex) {
            return "redirect:/books?error=" + ex.getMessage();
        }
    }

    @PostMapping
    public String saveBook(
            @RequestParam String name,
                              @RequestParam Float price,
                              @RequestParam Integer quantity,
                              @RequestParam Optional<Long> authorId,
                              @RequestParam Optional<Long> categoryId,
            @Valid Book book,
            BindingResult bindingResult,
            @RequestParam MultipartFile image,
            Model model) throws IOException {

        if (bindingResult.hasErrors()) {
            List<Author> authors = this.authorService.findAll();
            model.addAttribute("authors", authors);
            return "add-book";
        }
        try {
            this.bookService.saveBook(book, image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.bookService.saveBook(name,price,quantity,authorId.orElse(45L),categoryId.orElse(34L));
        List<Book> books = this.bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @PostMapping("/{id}/delete")
    public String deleteBookWithPost(@PathVariable Long id) {
        this.bookService.deleteById(id);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteBookWithDelete(@PathVariable Long id) {
        this.bookService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/get")
    public List<Book> getBooks() {
        return bookService.findAll();
    }

}
