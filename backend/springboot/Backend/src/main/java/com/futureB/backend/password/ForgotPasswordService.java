package com.futureB.backend.password;

import com.futureB.backend.Entity.User;
import com.futureB.backend.exception.InvalidTokenException;
import com.futureB.backend.exception.UserNotFoundException;
import com.futureB.backend.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ForgotPasswordService {
    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final PasswordEncoder passwordEncoder;


    public String sendEmail(String email) throws MessagingException, UnsupportedEncodingException {
        User user = userRepository.findByEmailId(email)
                .orElseThrow(()-> new UserNotFoundException("User with " + email + " does not Exist"));
        ForgotPasswordToken forgotPasswordToken = new ForgotPasswordToken(user);
        forgotPasswordRepository.save(forgotPasswordToken);
        String emailLink = "http://localhost:3000/reset-password?token=" + forgotPasswordToken.getToken();
        sendEmailToResetPassword(user.getUsername(), "Password Reset Link", emailLink);
        return "Password Reset Link Sent";
    }

    public void sendEmailToResetPassword(String to, String subject, String emailLink)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

        String emailContent = "<p>Hello<p/>"
                               + "Click the link below to reset password"
                               + "<p><a href=\"" + emailLink + "\">Change Password</a></p>"
                               + "Ignore this email if you did not make the request";

        messageHelper.setText(emailContent, true);
        messageHelper.setFrom("futurebproject@gmail.com", "Markata App Support");
        messageHelper.setSubject(subject);
        messageHelper.setTo(to);
        javaMailSender.send(mimeMessage);

    }

    public String resetPassword(String token, String password) {
        ForgotPasswordToken forgotPasswordToken = forgotPasswordRepository.findByToken(token)
                .orElseThrow(()-> new InvalidTokenException("Invalid Token"));
        if(isExpired(forgotPasswordToken)){
             throw new InvalidTokenException("Token is already Expired");
        }
        if(forgotPasswordToken.isUsed()){
            throw new InvalidTokenException("Token has already been used");
        }
        User user = forgotPasswordToken.getUser();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        forgotPasswordToken.setUsed(true);
        forgotPasswordRepository.save(forgotPasswordToken);

        return "Password reset successfully";


    }
    public boolean isExpired(ForgotPasswordToken token){
        return LocalDateTime.now().isAfter(token.getDateTime());
    }

}
