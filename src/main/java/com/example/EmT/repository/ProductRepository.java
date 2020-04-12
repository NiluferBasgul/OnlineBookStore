package com.example.EmT.repository;

import com.example.EmT.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();
    List<Product> findAllSortedByPrice(boolean asc);
    List<Product> findAllByManufacturerId(Long manufacturerId);
    List<Product> findAllByCategoryId(Long categoryId);
    Optional<Product> findById(Long id);
    Product save(Product product);
    void deleteById(Long id);
}
