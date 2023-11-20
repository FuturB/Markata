package com.futureB.backend.repository;

import com.futureB.backend.Entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query("""
            select t from Token t inner join User u on t.user.emailId = u.emailId
            where u.emailId = :emailId and (t.expired = false or t.revoked = false)
            """)
    List<Token> findAllValidTokensByUser(Integer emailId);

    Optional<Token> findByToken(String token);

}
