package com.futureB.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.futureB.backend.Entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
        public Optional<User> findByEmailId(String emailId);

        public User findById(long Id);

//        List<?> findByPlaceIgnoreCaseContaining;
        List<User> findByfirstNameIgnoreCaseContaining (String name);
}
