package com.futureB.backend.Service;

import com.futureB.backend.Entity.ActivationToken;
import com.futureB.backend.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;


        public void sendActivationEmail(User recipent, ActivationToken activationToken){
        this.sendEmail(
                recipent.getEmailId(),
                "futurebproject@gmail.com",
                "Activate Your Markata Account",
                "To confirm your account, please click here : "
                        +"http://localhost:3000/users/activate-account?token=" + activationToken.getConfirmationToken()
//                        +"http://localhost:8080/users/activate-account?token=" + activationToken.getConfirmationToken()

        );
        System.out.println("This is an activation token" + activationToken.getConfirmationToken());
    }

    public void sendEmail(String toEmail, String fromEmail, String subject, String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setFrom(fromEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);

        javaMailSender.send(simpleMailMessage);
    }
}
