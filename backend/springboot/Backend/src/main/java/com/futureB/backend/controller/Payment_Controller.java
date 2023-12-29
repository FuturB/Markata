package com.futureB.backend.controller;

import com.futureB.backend.dtos.PaymentDTO;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.futureB.backend.Service.PaymentService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class Payment_Controller {

    private final PaymentService paymentService;
    @PostMapping("card-payment")
    public String initiatePayment(@RequestBody PaymentDTO paymentDTO) throws StripeException{
        return paymentService.handlePayment(paymentDTO).getStatus();
    }
}
