package com.futureB.backend.controller;

import com.futureB.backend.Entity.Product;
import com.futureB.backend.Service.ProductService;
import com.futureB.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class ProductController {

//    @Autowired
//    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> getProducts() {
        // return productService.getProducts();
        return productRepository.findAll();
    }

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        //if(!userRepository.findByEmailId(user.getEmailId()).isPresent())

        if (!productRepository.findByProductId(product.getProductId()).isPresent()) {
            productRepository.save(product);
//productRepository.
            return ResponseEntity.ok("Successfully added");
        }
        return ResponseEntity.status(409).body("Product Already Exist");
    }
//    {
//			"name": "milk",
//          "type": "drink",
//			"description": "organic cow milk",
//			"price": "30",
//
//	}
//{
//
//                "name": "Running Shoes",
//                "type": "Footwear",
//                "description": "Lightweight and comfortable running shoes",
//                "price": "79.99"
//    }
@GetMapping("/product/{productId}")
public ResponseEntity<?> findProductById(@PathVariable Long productId) {
    Optional<Product> optionalProduct = productRepository.findByProductId(productId);

    if (optionalProduct.isPresent()) {
        Product product = optionalProduct.get();
        return ResponseEntity.ok().body(product);
    } else {
        String errorMessage = "Product not found";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long productId) {
        Optional<Product> optionalProduct = productRepository.findByProductId(productId);

        if (optionalProduct.isPresent()) {
            productRepository.deleteById(productId);
            return ResponseEntity.ok().body("Product deleted successfully");
        } else {
            String errorMessage = "Product not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

}

