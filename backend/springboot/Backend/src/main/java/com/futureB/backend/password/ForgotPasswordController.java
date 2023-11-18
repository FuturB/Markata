package com.futureB.backend.password;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/reset-password")
public class ForgotPasswordController {
    private final ForgotPasswordService  forgotPasswordService;

    @PostMapping
    public String requestPasswordReset(@RequestParam("email") String email) throws MessagingException, UnsupportedEncodingException {
       return forgotPasswordService.sendEmail(email);

    }
    @GetMapping
    public String changePassword(@RequestBody ChangePasswordRequest request){
        return forgotPasswordService.resetPassword(request.getToken(), request.getNewPassword());
    }

}
