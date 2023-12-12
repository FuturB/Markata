package com.futureB.backend.repository;

import com.futureB.backend.Entity.Product;
import com.futureB.backend.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public Optional<Product> findByProductId(Long productId);

    Page<Product> findBynameContainingIgnoreCase(String name, Pageable page);
//    public Optional<Product> findByProductId(Long productId);
}
