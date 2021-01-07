package ru.mirotvortsev.product_catalog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mirotvortsev.product_catalog.models.Product;
import ru.mirotvortsev.product_catalog.repo.ProductRepository;

@Controller
public class CatalogController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String productMain(Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "main";
    }

    @GetMapping("/add_new_product")
    public String productAdd(Model model) {
        return "add_new_product";
    }

    @PostMapping("/add_new_product")
    public String productAdd(@RequestParam String name, @RequestParam String description, Model model) {
        Product product = new Product(name, description);
        productRepository.save(product);
        return "redirect:/";
    }


}