package com.example.pharmacy.Controller;

import com.example.pharmacy.entities.Product;
import com.example.pharmacy.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductC {
    private final ProductRepository productR;

    public ProductC(ProductRepository productR) {
        this.productR = productR;
    }
    @GetMapping("/Product")
    public List<Product> getProducts() {
        return productR.findAll();
    }
    @PostMapping("/Product")
    public Product addProduct(@RequestBody Product product) {
        return productR.save(product);
    }
    @PutMapping("/Product/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product newProduct) {

        return productR.findById(id).map(product -> {
            product.setName(newProduct.getName());
            product.setPrice(newProduct.getPrice());
            product.setQuantity(newProduct.getQuantity());
            product.setDescription(newProduct.getDescription());
            return productR.save(product);
        }).orElseThrow(()->new RuntimeException("not found"));
    }
    @DeleteMapping("/Product/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productR.deleteById(id);
    }
}

