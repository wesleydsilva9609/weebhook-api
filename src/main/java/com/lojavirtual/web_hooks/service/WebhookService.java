package com.lojavirtual.web_hooks.service;

import com.lojavirtual.web_hooks.dto.WebhookStripeDTO;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

@Service
public class WebhookService {
    @Autowired
    private VendasService vendasService;
    @Value("${stripe.webhook-secret}")
    private String webhookSecret;

    public ResponseEntity<String> receberWebhook(HttpServletRequest request) throws IOException {
        String payload = new BufferedReader(request.getReader()).lines().collect(Collectors.joining("\n"));
        String sigHeader = request.getHeader("Stripe-Signature");

        try {
            Event event = Webhook.constructEvent(payload, sigHeader, webhookSecret);

            if ("checkout.session.completed".equals(event.getType())) {
                EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
                if (dataObjectDeserializer.getObject().isPresent()) {
                    Session session = (Session) dataObjectDeserializer.getObject().get();


                    String vendaid = session.getMetadata().get("venda");

                    System.out.println("Pagamento confirmado: " + vendaid);
                    vendasService.confirmarPagamento(vendaid);
                } else {
                    System.out.println("Evento não recebido corretamente (sem objeto)");
                }

                return ResponseEntity.ok().body("Webhook recebido com sucesso");
            }

        } catch (SignatureVerificationException ex) {
            System.err.println("Erro ao validar assinatura do Stripe: " + ex.getMessage());
            return ResponseEntity.badRequest().body("Erro ao validar assinatura do Stripe");
        }

        // Return default se o evento não for tratado
        return ResponseEntity.ok().body("Evento ignorado");
    }

}
