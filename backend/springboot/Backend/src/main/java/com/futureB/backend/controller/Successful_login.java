package com.futureB.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class Successful_login {
    @GetMapping("/success")
    public ResponseEntity<String> success() {

        return ResponseEntity.status(200).body("Successful Login");
    }
}
