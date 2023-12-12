package com.futureB.backend.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.futureB.backend.Service.ProductService;
import com.futureB.backend.exception.ProductNotFoundException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String name;
    private String type;
    private String description;
    private Double price;
    private Integer quantityInStock;
    @ManyToOne
    @JoinColumn(name = "product_category_id")
    @JsonBackReference
    private ProductCategory productCategory;
// private  BufferedImage productImage;
//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//    private List<ProductItem> productItems;
//    public Product(Long productId, ProductService productService) {
//        Product product = productService.getProductById(productId)
//                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));
//
//        // Assigning fields from the fetched product
//        this.productId = product.getProductId();
//        this.name = product.getName();
//        this.type = product.getType();
//        this.description = product.getDescription();
//        this.price = product.getPrice();
//        this.quantityInStock = product.getQuantityInStock();
//    }

}
