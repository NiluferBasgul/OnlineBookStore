package com.example.EmT.model;

import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Book {

    private Long id;
    private String name;

    @NotNull
    @Min(value = 1, message = "Number of books must be bigger than 1")
    private Long numberofBooks;


    @NotNull
    @Min(value = 2, message = "Price must be bigger than 1")
    private Float price;


    @NotNull
    private Author author;
    private Category category;
    private String imageBase64;


    public Book(Long id,
        String name,
        Long numberOfBooks,
        Float price,
        Author author,
        Category category) {
        this.id = id;
        this.name = name;
        this.numberofBooks = numberOfBooks;
        this.price = price;
        this.author = author;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getnumberofBooks() { return numberofBooks; }

    public void setnumberofBooks(Long numberofBooks) { this.numberofBooks = numberofBooks; }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() { return category; }

    public void setCategory(Category category) { this.category = category; }

    public String getImageBase64(){ return imageBase64; }

    public void setImageBase64(String imageBase64) { this.imageBase64 = imageBase64; }

}

