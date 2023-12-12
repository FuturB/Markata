package com.futureB.backend.Service;

import com.futureB.backend.dtos.ProductDTO;
import com.futureB.backend.Entity.Product;
import com.futureB.backend.config.JwtService;
import com.futureB.backend.repository.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    private final JwtService jwtService;

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


public Product findProductById(Long productId)
{
    //if()
    return productRepository.findByProductId(productId).get();
}

public Page<Product> findBynameContainingIgnoreCase(String name, Pageable pageable){
        return productRepository.findBynameContainingIgnoreCase(name,pageable);
}

    public String extractEmailId(HttpServletRequest request){
        final String jwt;
        final String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return "wrong Authorization";
        }
        jwt = authHeader.substring(7);
        System.out.println(jwt);
        return jwtService.extractUsername(jwt);

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
