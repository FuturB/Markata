package com.futureB.backend.Service;

import com.futureB.backend.Entity.Product;
import com.futureB.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

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
}
