package com.example.EmT.service.impl;

import com.example.EmT.model.Manufacturer;
import com.example.EmT.model.Product;
import com.example.EmT.model.exception.ProductNotFoundException;
import com.example.EmT.repository.ProductRepository;
import com.example.EmT.service.ManufacturerService;
import com.example.EmT.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ManufacturerService manufacturerService;

    public ProductServiceImpl(ProductRepository productRepository,
                              ManufacturerService manufacturerService) {
        this.productRepository = productRepository;
        this.manufacturerService = manufacturerService;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public List<Product> findAllByManufacturerId(Long manufacturerId) {
        return this.productRepository.findAllByManufacturerId(manufacturerId);
    }

    @Override
    public List<Product> findAllByCategoryId(Long categoryId){
        return this.productRepository.findAllByCategoryId(categoryId);
    }
    @Override
    public List<Product> findAllSortedByPrice(boolean asc) {
        return this.productRepository.findAllSortedByPrice(asc);
    }

    @Override
    public Product findById(Long id) {
        return this.productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Product saveProduct(String name, Float price, Integer quantity, Long manufacturerId,
        Long categoryId) {
        return null;
    }


    @Override
    public Product saveProduct(Product product, MultipartFile image) throws IOException {
        Manufacturer manufacturer = this.manufacturerService.findById(product.getManufacturer().getId());
        product.setManufacturer(manufacturer);
        if (image != null && !image.getName().isEmpty()) {
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
            product.setImageBase64(base64Image);
        }
        return this.productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product, MultipartFile image) throws IOException {
        Product p = this.findById(id);
        Manufacturer manufacturer = this.manufacturerService.findById(product.getManufacturer().getId());
        p.setManufacturer(manufacturer);
//        p.setPrice(product.getPrice());
        if (image != null && !image.getName().isEmpty()) {
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
            p.setImageBase64(base64Image);
        }
        return this.productRepository.save(p);
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}
