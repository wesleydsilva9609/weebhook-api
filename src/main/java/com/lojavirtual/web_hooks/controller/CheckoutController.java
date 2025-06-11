package com.lojavirtual.web_hooks.controller;


import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.service.CheckoutService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.stripe.model.checkout.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;

import java.util.Map;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    @GetMapping("/success")
    public ResponseEntity<String> checkoutSuccess() {
        return new ResponseEntity<>("Checkout success", HttpStatus.OK);
    }

    @GetMapping("/cancel")
    public ResponseEntity<String> cancelCheckout() {
        return new ResponseEntity<>("Checkout cancelled", HttpStatus.OK);
    }


    @PostMapping("/create-session/{vendaid}")
    @Transactional
    public ResponseEntity<Map<String, Object>> criando(@PathVariable String vendaid) {
        try {
            // Parâmetros da sessão de checkout
            SessionCreateParams params = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl("http://localhost:8080/success")
                    .setCancelUrl("http://localhost:8080/cancel")
                    .setCustomerEmail("test@webHook.com")
                    .putMetadata("venda", vendaid)
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setQuantity(1L)
                                    .setPriceData(
                                            SessionCreateParams.LineItem.PriceData.builder()
                                                    .setCurrency("usd")
                                                    .setUnitAmount(2000L) // valor em cents (20 USD)
                                                    .setProductData(
                                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                    .setName("Produto Teste")
                                                                    .build()
                                                    )
                                                    .build()
                                    )
                                    .build()
                    )
                    .build();

            // Cria a sessão de checkout corretamente
            Session session = Session.create(params);

            // Retorna apenas o que queremos (id e url)
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("id", session.getId());
            responseData.put("url", session.getUrl());

            return ResponseEntity.ok(responseData);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Erro ao criar sessão: " + e.getMessage()));
        }
    }
}