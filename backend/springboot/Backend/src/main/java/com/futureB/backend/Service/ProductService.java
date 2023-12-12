package com.futureB.backend.Service;

import com.futureB.backend.dtos.ProductDTO;
import com.futureB.backend.Entity.Product;
import com.futureB.backend.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

@Autowired
    private ModelMapper modelMapper;

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

//    public ProductDTO saveProduct(ProductDTO productDTO) {
//        Product product = convertToEntity(productDTO);
//        Product savedProduct = productRepository.save(product);
//        return convertToDTO(savedProduct);
//    }
    public Product saveProduct(Product product) {
//        Product product = convertToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    public Optional<ProductDTO> getProductById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.map(this::convertToDTO);
    }
    public boolean deleteProduct(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            productRepository.delete(optionalProduct.get());
            return true;
        } else {
            return false;
        }
    }
    // Helper methods for conversion
  ProductDTO convertToDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

  Product convertToEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }
}

//import com.futureB.backend.Entity.Product;
//import com.futureB.backend.repository.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ProductService {
//    @Autowired
//    private ProductRepository productRepository;
//
//    public List<Product> getProducts()
//    {
//
//        return productRepository.findAll();
//    }
//
//
//public Product saveProduct(Product product)
//{
//
//    return productRepository.save(product);
//}
//public Optional<Product> findProductById(Long productId)
//{
//    //if()
//    return productRepository.findByProductId(productId);
//}
//}
