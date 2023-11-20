package com.futureB.backend.controller;

import com.futureB.backend.Entity.Token;
import com.futureB.backend.Entity.TokenType;
import com.futureB.backend.config.JwtService;
import com.futureB.backend.repository.TokenRepository;
import com.futureB.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println("Request: " + request);
//        request.setPassword(passwordEncoder.encode(request.getPassword()));
        System.out.println("Request get: " + request.getEmailId() + " : " + request.getPassword());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmailId(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmailId(request.getEmailId())
                .orElseThrow();
        System.out.println("This is the user fetched from database by email" + user);
        var jwtToken = jwtService.generateToken(user);
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
