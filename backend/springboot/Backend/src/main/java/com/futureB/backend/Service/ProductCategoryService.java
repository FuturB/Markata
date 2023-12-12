package com.futureB.backend.Service;

import com.futureB.backend.Entity.Product;
import com.futureB.backend.Entity.ProductCategory;
import com.futureB.backend.dtos.ProductCategoryDTO;
import com.futureB.backend.dtos.ProductDTO;
//import com.futureB.backend.dtos.ProductDto;
import com.futureB.backend.exception.CategoryNotFoundException;
import com.futureB.backend.exception.ProductNotFoundException;
import com.futureB.backend.repository.ProductCategoryRepository;
import com.futureB.backend.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<ProductCategoryDTO> getAllCategories() {
        List<ProductCategory> productCategories = productCategoryRepository.findAll();
        List<ProductCategoryDTO> productCategoryDtos = new ArrayList<>();

        productCategories.forEach(productCategory -> {
            Set<Product> products = productCategory.getProductSet();

            // Convert Set<Product> to Set<ProductDto>
            Set<ProductDTO> productDtoList = products.stream()
                    .map(product -> modelMapper.map(product, ProductDTO.class))
                    .collect(Collectors.toSet());

            ProductCategoryDTO productCategoryDto = modelMapper.map(productCategory, ProductCategoryDTO.class);

            // Set the Set<ProductDto> in ProductCategoryDto
            productCategoryDto.setProductSet(productDtoList);

            productCategoryDtos.add(productCategoryDto);
        });
        return productCategoryDtos;
    }


    public ProductCategoryDTO getCategory(Long id) {
        ProductCategory category = productCategoryRepository.findById(id).orElseThrow();
        return modelMapper.map(category, ProductCategoryDTO.class);
    }

public String addCategory(ProductCategoryDTO categoryDto) {
    ProductCategory category = modelMapper.map(categoryDto, ProductCategory.class);

    // Check if a category with the same name already exists
    ProductCategory existingCategory = productCategoryRepository.findByName(category.getName());
    if (existingCategory != null) {
        return "Category with the same name already exists";
    }

    // Save the new category
    productCategoryRepository.save(category);
    return "Category Added Successfully";
}

    public void deleteCategory(Long categoryId) {
        productCategoryRepository.deleteById(categoryId);
    }

    public ProductCategoryDTO addProductToCategory(Long categoryId, Long productId) {
        ProductCategory category = productCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        if (!category.getName().equalsIgnoreCase(product.getType())) {
            throw new IllegalArgumentException("Product type does not match the category name");
        }

            category.getProductSet().add(product);
            product.setProductCategory(category);

            productCategoryRepository.save(category);
            productRepository.save(product);
            ProductCategoryDTO productCategoryDTO = modelMapper.map(category, ProductCategoryDTO.class);

        return productCategoryDTO;
    }

}
