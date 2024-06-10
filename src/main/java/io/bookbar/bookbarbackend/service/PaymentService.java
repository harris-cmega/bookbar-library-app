// PaymentService.java

package io.bookbar.bookbarbackend.service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import io.bookbar.bookbarbackend.dto.PaymentDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${stripe.apiKey}")
    private String stripeApiKey;

    public PaymentIntent createPaymentIntent(PaymentDTO paymentDTO) throws StripeException {
        com.stripe.Stripe.apiKey = stripeApiKey;

        if (paymentDTO == null) {
            throw new NullPointerException("PaymentDTO is null");
        }
        if (paymentDTO.getAmount() == null) {
            throw new NullPointerException("Payment amount is null");
        }
        System.out.println("Creating payment intent for amount: " + paymentDTO.getAmount());

        Map<String, Object> params = new HashMap<>();
        params.put("amount", (int) (paymentDTO.getAmount() * 100)); // Stripe amount is in cents
        params.put("currency", "usd");
        params.put("payment_method_types", new String[]{"card"});

        PaymentIntent paymentIntent = PaymentIntent.create(params);
        System.out.println("Payment intent created: " + paymentIntent.getId());
        return paymentIntent;
    }

    public ResponseEntity<String> stripePayment(String userEmail) {
        // Your payment completion logic here
        return new ResponseEntity<>("Payment completed", HttpStatus.OK);
    }
}
