package com.futureB.backend.controller;

import com.futureB.backend.dtos.PaymentDTO;
import com.stripe.exception.StripeException;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class Payment_Controller {

    final int PaymentServices paymentServices;
    @PostMapping("card-payment")
    public String initiatePayment(@RequestBody PaymentDTO paymentDTO) throws StripeException{
        PaymentIntentCreateParams createParams = PaymentIntentCreateParams.builder()
                .setAmount(new Long(cal))
        return "hi";
    }
}
