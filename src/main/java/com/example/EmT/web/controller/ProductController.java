package com.example.EmT.web.controller;

import com.example.EmT.model.Manufacturer;
import com.example.EmT.model.Product;
import com.example.EmT.service.ManufacturerService;
import com.example.EmT.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private ManufacturerService manufacturerService;

    public ProductController(ProductService productService,
                             ManufacturerService manufacturerService) {
        this.productService = productService;
        this.manufacturerService = manufacturerService;
    }

    //    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String getProductPage(Model model) {
        List<Product> products = this.productService.findAll();
        model.addAttribute("products", products);
        return "products";
    }


//    //    @RequestMapping(method = RequestMethod.GET, value = "/add-new")
//    @GetMapping("/add-new")
//    public String addNewProductPage(Model model) {
//        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
//        model.addAttribute("manufacturers", manufacturers);
//        model.addAttribute("product", new Product());
//        return "add-product";
//    }

    @GetMapping("/{id}/edit")
    public String editProductPage(Model model, @PathVariable Long id) {
        try {
            Product product = this.productService.findById(id);
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();
            model.addAttribute("product", product);
            model.addAttribute("manufacturers", manufacturers);
            return "add-product";
        } catch (RuntimeException ex) {
            return "redirect:/products?error=" + ex.getMessage();
        }
    }

    @PostMapping
    public String saveProduct(
//            @RequestParam String name,
//                              @RequestParam Float price,
//                              @RequestParam Integer quantity,
//                              @RequestParam Long manufacturerId,
            @Valid Product product,
            BindingResult bindingResult,
            @RequestParam MultipartFile image,
            Model model) {

        if (bindingResult.hasErrors()) {
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();
            model.addAttribute("manufacturers", manufacturers);
            return "add-product";
        }
        try {
            this.productService.saveProduct(product, image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //this.productService.saveProduct(name, price, quantity, manufacturerId);
//        List<Product> products = this.productService.findAll();
//        model.addAttribute("products", products);
//        return "products";
        return "redirect:/products";
    }

    @PostMapping("/{id}/delete")
    public String deleteProductWithPost(@PathVariable Long id) {
        this.productService.deleteById(id);
        return "redirect:/products";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteProductWithDelete(@PathVariable Long id) {
        this.productService.deleteById(id);
        return "redirect:/products";
    }
}
