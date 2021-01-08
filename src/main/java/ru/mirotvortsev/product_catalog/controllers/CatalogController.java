package ru.mirotvortsev.product_catalog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mirotvortsev.product_catalog.models.Product;
import ru.mirotvortsev.product_catalog.repo.ProductRepository;

import java.util.ArrayList;
import java.util.Optional;

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

    @GetMapping("/{id}/edit")
    public String productEdit(@PathVariable(value = "id") long id, Model model) {
        if (!productRepository.existsById(id)) {
            return "redirect:/";
        }
        Optional<Product> product = productRepository.findById(id);
        ArrayList<Product> res = new ArrayList<>();
        product.ifPresent(res::add);
        model.addAttribute("product", res);
        return "edit";
    }


    @PostMapping("/{id}/edit")
    public String productPostUpdate(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam String description, Model model) {
        Product product = productRepository.findById(id).orElseThrow(IllegalStateException::new);
        product.setName(name);
        product.setDescription(description);
        productRepository.save(product);
        return "redirect:/";
    }


    @PostMapping("/{id}/remove")
    public String productPostDelete(@PathVariable(value = "id") long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(IllegalStateException::new);
        productRepository.delete(product);
        return "redirect:/";
    }

}