package com.futureB.backend.password;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@RequiredArgsConstructor
@RestController
public class ForgotPasswordController {
    private final ForgotPasswordService  forgotPasswordService;

//    @PostMapping("/api/v1/reset-password")
//    public String requestPasswordReset(@RequestParam("email") String email){
//       return forgotPasswordService.sendEmail(email);
//
//    }
    @PostMapping("/api/v1/reset-password")
    public ResponseEntity<String> requestPasswordReset(@RequestParam("email") String email) {
        String responseMessage = forgotPasswordService.sendEmail(email);

        // Customize the response based on your service logic
        if ("success".equals(responseMessage)) {
            return ResponseEntity.ok("Password reset email sent successfully");
        } else {
            // You might want to include more details in the response
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to send password reset email");
        }
    }
}
