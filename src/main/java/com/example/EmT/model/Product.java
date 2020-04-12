package com.example.EmT.model;

import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Product {

    private Long id;
    private String name;
    private Long numberofBooks;


    @NotNull
    @Min(value = 2, message = "Price must be bigger than 2")
    private Float price;
    private Integer quantity;

    @NotNull
    private Manufacturer manufacturer;

    private Category category;

    // private MultipartFile photoBooks;

    private String imageBase64;


    public Product(Long id,
        String name,
        Long numberOfBooks,
        Float price,
        Integer quantity,
        Manufacturer manufacturer,
        Category category) {
        this.id = id;
        this.name = name;
        this.numberofBooks = numberOfBooks;
        this.price = price;
        this.quantity = quantity;
        this.manufacturer = manufacturer;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Category getCategory() { return category; }

    public void setCategory(Category category) { this.category = category; }

    public String getImageBase64(){ return imageBase64; }

    public void setImageBase64(String imageBase64) { this.imageBase64 = imageBase64; }

//    public MultipartFile getphotoBooks() { return photoBooks; }
//
//    public void setImage(MultipartFile photoBooks) { this.photoBooks = photoBooks; }

}

