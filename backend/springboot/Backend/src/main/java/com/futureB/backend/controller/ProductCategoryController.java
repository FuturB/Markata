package com.futureB.backend.controller;

import com.futureB.backend.Entity.ProductCategory;
import com.futureB.backend.Service.ProductCategoryService;
import com.futureB.backend.dtos.ProductCategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories/")
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    @GetMapping
    public ResponseEntity<?> getAllProductCategories(){
        List<ProductCategoryDTO> categoryDtos = productCategoryService.getAllCategories();
        return ResponseEntity.ok(categoryDtos);

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductCategory(@PathVariable Long id){
        ProductCategoryDTO category = productCategoryService.getCategory(id);
        return ResponseEntity.ok(category);

    }
    @PostMapping
    public ResponseEntity<String> addProductCategory(@RequestBody ProductCategoryDTO categoryDto) {
        String result = productCategoryService.addCategory(categoryDto);
        HttpStatus status = result.startsWith("Category Added") ? HttpStatus.CREATED : HttpStatus.CONFLICT;
        return new ResponseEntity<>(result, status);
    }
    @PostMapping("/{categoryId}/addProduct/{productId}")
    public ResponseEntity<ProductCategoryDTO> addProductToCategory(@PathVariable Long categoryId, @PathVariable Long productId) {
      ProductCategoryDTO category =  productCategoryService.addProductToCategory(categoryId, productId);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        productCategoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
