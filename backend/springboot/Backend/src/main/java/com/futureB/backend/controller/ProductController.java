package com.futureB.backend.controller;

import com.futureB.backend.dtos.ProductDTO;
import com.futureB.backend.Entity.Product;
import com.futureB.backend.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@RestController
//@RequestMapping("api/v1/")
//public class ProductController {

//    @Autowired
//    private ProductService productService;
//    @Autowired
//    private ProductRepository productRepository;
//
//    @GetMapping("/products")
//    public List<ProductDTO> getAllProducts() {
//        return productService.getAllProducts();
//      //  return productRepository.findAll();
//    }
//
//    @PostMapping("/addProduct")
//    public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO) {
//        //if(!userRepository.findByEmailId(user.getEmailId()).isPresent())
//
//        if (!productRepository.findByProductId(product.getProductId()).isPresent()) {
//            productRepository.save(product);
////productRepository.
//            return ResponseEntity.ok("Successfully added");
//        }
//        return ResponseEntity.status(409).body("Product Already Exist");
//    }
////    {
////			"name": "milk",
////          "type": "drink",
////			"description": "organic cow milk",
////			"price": "30",
////          "quantitInStock":"30"
////
////	}
////{
////
////                "name": "Running Shoes",
////                "type": "Footwear",
////                "description": "Lightweight and comfortable running shoes",
////                "price": "79.99"
////                "quantitInStock":"50"
////    }
//@GetMapping("/product/{productId}")
//public ResponseEntity<?> findProductById(@PathVariable Long productId) {
//    Optional<ProductDTO> optionalProduct = productService.getProductById(productId);
//
//    if (optionalProduct.isPresent()) {
//        ProductDTO productDTO = optionalProduct.get();
//        return ResponseEntity.ok().body(productDTO);
//    } else {
//        String errorMessage = "Product not found";
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
//    }
//}
//
//    @DeleteMapping("/product/{productId}")
//    public ResponseEntity<?> deleteProductById(@PathVariable Long productId) {
//
//        Optional<Product> optionalProduct = productRepository.findByProductId(productId);
//
//        if (optionalProduct.isPresent()) {
//            productRepository.deleteById(productId);
//            return ResponseEntity.ok().body("Product deleted successfully");
//        } else {
//            String errorMessage = "Product not found";
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
//        }
//    }


@RestController
@RequestMapping("api/v1/product")
    public class ProductController {

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
    }

