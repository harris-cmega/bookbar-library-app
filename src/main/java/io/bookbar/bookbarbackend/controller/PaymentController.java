package io.bookbar.bookbarbackend.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import io.bookbar.bookbarbackend.dto.PaymentDTO;
import io.bookbar.bookbarbackend.security.JwtUtils;
import io.bookbar.bookbarbackend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/payment/secure")
public class PaymentController {

    private final PaymentService paymentService;
    private final JwtUtils jwtUtils;

    @Autowired
    public PaymentController(PaymentService paymentService, JwtUtils jwtUtils) {
        this.paymentService = paymentService;
        this.jwtUtils = jwtUtils;
    }

    @PostConstruct
    public void init() {
        Stripe.apiKey = "sk_test_51PPiDJ02eNUXNagOZBQMa5LEPEZvIJ12LoZEdzrjdFZE2vPj8RYkect4HZc8d6a3crNA8ncGSbVBIacqW0FyGclY001aSgrcMT";
    }

    @PostMapping("/payment-intent")
    public ResponseEntity<Map<String, String>> createPaymentIntent(@RequestBody PaymentDTO paymentInfoRequest) {
        try {
            System.out.println("Received payment info: " + paymentInfoRequest);
            PaymentIntent paymentIntent = paymentService.createPaymentIntent(paymentInfoRequest);
            Map<String, String> response = new HashMap<>();
            response.put("client_secret", paymentIntent.getClientSecret());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (StripeException e) {
            System.err.println("Failed to create payment intent: " + e.getMessage());
            return new ResponseEntity<>(Map.of("error", "Failed to create payment intent: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            return new ResponseEntity<>(Map.of("error", "An unexpected error occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/payment-complete")
    public ResponseEntity<String> stripePaymentComplete(@RequestHeader(value="Authorization") String token) throws Exception {
        if (!jwtUtils.validateToken(token)) {
            throw new Exception("Invalid token");
        }
        String userEmail = jwtUtils.getUsernameFromToken(token);
        if (userEmail == null) {
            throw new Exception("User email is missing");
        }
        return paymentService.stripePayment(userEmail);
    }

    @PostMapping("/create-checkout-session")
    public ResponseEntity<Map<String, String>> createCheckoutSession(@RequestBody PaymentDTO paymentInfoRequest) {
        try {
            List<SessionCreateParams.LineItem> lineItems = paymentInfoRequest.getProducts().stream()
                    .map(product -> {
                        double bookPrice = paymentService.getBookPriceByTitle(product);
                        return SessionCreateParams.LineItem.builder()
                                .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                        .setCurrency("usd")
                                        .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                .setName(product)
                                                .build())
                                        .setUnitAmount((long) (bookPrice * 100)) // Amount in cents
                                        .build())
                                .setQuantity(1L)
                                .build();
                    })
                    .collect(Collectors.toList());

            SessionCreateParams params = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl("http://localhost:3000/success")
                    .setCancelUrl("http://localhost:3000/cancel")
                    .addAllLineItem(lineItems)
                    .build();

            Session session = Session.create(params);
            Map<String, String> response = new HashMap<>();
            response.put("id", session.getId());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (StripeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
