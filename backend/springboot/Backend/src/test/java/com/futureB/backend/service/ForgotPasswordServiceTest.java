package com.futureB.backend.service;

import com.futureB.backend.Entity.User;
import com.futureB.backend.exception.InvalidTokenException;
import com.futureB.backend.exception.UserNotFoundException;
import com.futureB.backend.password.ForgotPasswordRepository;
import com.futureB.backend.password.ForgotPasswordService;
import com.futureB.backend.password.ForgotPasswordToken;
import com.futureB.backend.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ForgotPasswordServiceTest {
    @Mock
    private JavaMailSender mailSender;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ForgotPasswordRepository forgotPasswordRepository;
    @InjectMocks
    private ForgotPasswordService forgotPasswordService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Other setup if needed
    }

    @Test
    public void sendEmail_InvalidEmail_UserNotFoundException(){
        Mockito.lenient().when(userRepository.findByEmailId("test@example.com")).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, ()-> forgotPasswordService.sendEmail("test@example.com") );
    }

    @Test
    void resetPassword_ExpiredToken_InvalidTokenException() {
        ForgotPasswordToken expiredToken = new ForgotPasswordToken(new User());
        expiredToken.setDateTime(LocalDateTime.now().minusDays(2)); // Set token expiration to the past
        Mockito.lenient().when(forgotPasswordRepository.findByToken(Mockito.anyString())).thenReturn(Optional.of(expiredToken));
        assertThrows(InvalidTokenException.class, () -> forgotPasswordService.resetPassword("expiredToken", "newPassword"));
    }
    @Test
    void resetPassword_UsedToken_InvalidTokenException() {
        ForgotPasswordToken usedToken = new ForgotPasswordToken(new User());
        usedToken.setUsed(true);
        Mockito.lenient().when(forgotPasswordRepository.findByToken(Mockito.anyString())).thenReturn(Optional.of(usedToken));
        assertThrows(InvalidTokenException.class, () -> forgotPasswordService.resetPassword("usedToken", "newPassword"));
    }


}
