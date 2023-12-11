package com.futureB.backend.Service;

import com.futureB.backend.Entity.Product;
import com.futureB.backend.config.JwtService;
import com.futureB.backend.repository.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    private final JwtService jwtService;

    public List<Product> getProducts()
    {
        return productRepository.findAll();
    }


public Product addProduct(Product product)
{

    return productRepository.save(product);
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

}
