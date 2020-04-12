package com.example.EmT.repository.impl;

import com.example.EmT.model.Product;
import com.example.EmT.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private HashMap<Long, Product> products;
    private Long counter;



    @PostConstruct
    public void init() {
        this.counter = 0L;
        this.products = new HashMap<>();
        Long id = this.generateKey();
        Long numberOfBooks = this.generateKey();
        this.products.put(id, new Product(id,"p1",numberOfBooks,45f,null,null,null));
        id = this.generateKey();
        numberOfBooks = this.generateKey();
        this.products.put(id, new Product(id,"p2",numberOfBooks, 45f,null,null,null));
    }


    @Override
    public List<Product> findAll() {
        return new ArrayList<>(this.products.values());
    }

    @Override
    public List<Product> findAllSortedByPrice(boolean asc) {
        Comparator<Product> priceComparator = Comparator.comparing(Product::getPrice);
        if (!asc) {
            priceComparator = priceComparator.reversed();
        }
        return this.products.values()
                .stream()
                .sorted(priceComparator)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllByManufacturerId(Long manufacturerId) {
        return this.products.values()
                .stream()
                .filter(item -> item.getManufacturer().getId().equals(manufacturerId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllByCategoryId(Long categoryId) {
        return this.products.values()
            .stream()
            .filter(item -> item.getCategory().getId().equals(categoryId))
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(this.products.get(id));
    }

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(this.generateKey());
        }
        this.products.put(product.getId(), product);
        return product;
    }

    @Override
    public void deleteById(Long id) {
        this.products.remove(id);
    }

    private Long generateKey() {
        return ++counter;
    }
}
