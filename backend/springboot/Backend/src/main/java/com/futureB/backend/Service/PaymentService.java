package com.futureB.backend.Service;

import com.futureB.backend.dtos.PaymentDTO;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethod;
import com.stripe.model.PaymentSourceCollection;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentIntentConfirmParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService {

    @Value("${stripe.apiKey}")
    private String secretKey;

    public Customer createCustomer(PaymentDTO paymentDTO) throws StripeException {
        Stripe.apiKey = secretKey;

        // Create a new Customer object with the customer's name and email
        CustomerCreateParams customerParams = new CustomerCreateParams.Builder()
                .setName(paymentDTO.getName())
                .setEmail(paymentDTO.getEmail())
                .setPaymentMethod(paymentDTO.getPaymentMethodId())
                .build();
        Customer customer = Customer.create(customerParams);
        customer.setSources(new PaymentSourceCollection());
        return customer;
    }
//    public static long calculateOrderAmount() {
//        // Replace this constant with a calculation of the order's amount
//        // Calculate the order total on the server to prevent
//        // people from directly manipulating the amount on the client
//        return 1400;
//    }

    public PaymentIntent handlePayment(PaymentDTO paymentDTO) throws StripeException {
        Stripe.apiKey = secretKey;

        Map<String, Object> params = new HashMap<>();
        params.put("amount", (long) (paymentDTO.getAmount() * 100));
        params.put("currency", paymentDTO.getCurrency());
        params.put("automatic_payment_methods[allow_redirects]", "never");
        params.put("automatic_payment_methods[enabled]", true);

        params.put("description", "Order Payment");

        Customer customer = createCustomer(paymentDTO);


        // Set up payment method
        // card number 4242424242424242 succeeds while
        Map<String, Object> paymentMethodParams = new HashMap<>();
        switch (paymentDTO.getType()) {
            case "card":
                paymentMethodParams.put("type", "card");
                paymentMethodParams.put("card[number]", paymentDTO.getCardNumber());
                paymentMethodParams.put("card[exp_month]", paymentDTO.getExp_month());
                paymentMethodParams.put("card[exp_year]", paymentDTO.getExp_year());
                paymentMethodParams.put("card[cvc]", paymentDTO.getCvc());
                params.put("payment_method", PaymentMethod.create(paymentMethodParams).getId());
                break;
//            case "sepa_debit":
//                paymentMethodParams.put("type", "sepa_debit");
//                paymentMethodParams.put("sepa_debit[iban]", paymentDTO.getIban());
//                params.put("payment_method_types[]", "sepa_debit");
//                params.put("payment_method_data[sepa_debit]", paymentMethodParams);
//                break;
            default:
                throw new IllegalArgumentException("Invalid payment method type");
        }

        // Create payment intent
        PaymentIntent paymentIntent = PaymentIntent.create(params);
        paymentIntent.setCustomer(customer.getId());
        System.out.println("PaymentIntent created: " + paymentIntent);

        // Confirm payment intent
        PaymentIntentConfirmParams confirmParams = new PaymentIntentConfirmParams.Builder()
                .setPaymentMethod(paymentIntent.getPaymentMethod())
                .build();
        try {
            paymentIntent.confirm(confirmParams);
            paymentIntent.setStatus("succeeded");
            System.out.println("PaymentIntent confirmed: " + paymentIntent);
//            paymentRepository.save(modelMapper.map(paymentDTO, Payment.class));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(paymentIntent);
        return paymentIntent;
    }

}
