package com.futureB.backend.password;

import com.futureB.backend.Entity.User;
import com.futureB.backend.exception.UserNotFoundException;
import com.futureB.backend.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class ForgotPasswordService {
    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;

    public String sendEmail(String email)  {
        User user = userRepository.findByEmailId(email).orElseThrow(()-> new UserNotFoundException("Username does not Exist"));
        if(user == null){
            return "Username does not Exist";
        }
        ForgotPasswordToken forgotPasswordToken = new ForgotPasswordToken(user);
        String emailLink = "http://localhost:8080/reset-password?token=" + forgotPasswordToken.getToken();
        try {
            sendEmailToResetPassword(user.getUsername(), "Password Reset Link", emailLink);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "Password Reset Link Sent";
    }

    public void sendEmailToResetPassword(String to, String subject, String emailLink) throws MessagingException, UnsupportedEncodingException {
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
}
