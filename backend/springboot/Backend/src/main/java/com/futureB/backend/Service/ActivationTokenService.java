package com.futureB.backend.Service;

import com.futureB.backend.Entity.ActivationToken;
import com.futureB.backend.Entity.User;
import com.futureB.backend.repository.ActivationTokenRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivationTokenService {
    private final ActivationTokenRepo activationTokenRepo;
    private final UserService userService;
    private final EmailService emailService;
    public ActivationToken createAndPersistActivationToken(User newRegisterdUser) {
        ActivationToken activationToken = new ActivationToken(newRegisterdUser);
        return activationTokenRepo.save(activationToken);
    }

    public boolean verifedAndAccountActivated(String token) {
        ActivationToken activationToken = this.tokenValid(token);
        if( activationToken != null  && !activationToken.getUser().isEnabled()){
            userService.activateUser(activationToken.getUser());
            //Todo: this is taking to much time hence solve after understanding multhithreading this needs it's own thread
//            emailService.sendEmail(
//                    activationToken.getUser().getEmailId(),
//                    "futurebproject@gmail.com",
//                    "Vola! Your Markata Registrion is COMPLETED",
//                    "Congrats your all set up! Enjoy Marakta"
//            );
            System.out.println("token " + !activationToken.getUser().isEnabled());
            return true;

        }else {
            return false;
        }
    }

    private ActivationToken tokenValid(String token) {

        return this.findToken(token);
    }

    private ActivationToken findToken(String token) {
        return activationTokenRepo.findActivationTokenByConfirmationToken(token);
    }

}
