package com.futureB.backend.controller;

import com.futureB.backend.dtos.ProductDTO;
import com.futureB.backend.Entity.Product;
import com.futureB.backend.Service.ProductService;
import com.futureB.backend.repository.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v2/")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


        private final ProductService productService;

        @Autowired
        public ProductController(ProductService productService) {
            this.productService = productService;
        }

        @GetMapping("/products")
        public ResponseEntity<List<ProductDTO>> getProducts() {
            List<ProductDTO> products = productService.getAllProducts();
            return ResponseEntity.ok(products);
        }

//        @PostMapping("/addProduct")
//        public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO) {
//            ProductDTO savedProduct = productService.saveProduct(productDTO);
//            return ResponseEntity.ok(savedProduct);
//        }
    @PostMapping("/addProduct")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(savedProduct);
    }
//    {
//        "name": "NIKE",
//            "type": "Clothing",
//            "description": "Mens casual shoes",
//            "price": 69.99,
//            "quantityInStock": 50
//
//    }
        @GetMapping("/{productId}")
        public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId) {
            Optional<ProductDTO> optionalProduct = productService.getProductById(productId);
            return optionalProduct.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{productId}")
        public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
            boolean deleted = productService.deleteProduct(productId);

            if (deleted) {
                return ResponseEntity.ok("Product deleted successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        }



    @GetMapping("/search-product")
    public Page<Product> getYourEntities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = true) String name
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        return productService.findBynameContainingIgnoreCase(name, pageable);
    }

    @GetMapping("/Current-user")
    public String currentUser(HttpServletRequest request){
        return productService.extractEmailId(request);
    }


}

