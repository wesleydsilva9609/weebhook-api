package com.lojavirtual.web_hooks.service;

import com.lojavirtual.web_hooks.dto.WebhookStripeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WebhookService {
    @Autowired
    private VendasService vendasService;

    public ResponseEntity<String> receberWebhook(WebhookStripeDTO payload) {
        var vendaId = payload.data().object().metadata().get("venda");
        System.out.println("webhook recebido " + vendaId);
        vendasService.confirmarPagamento(vendaId);

        return ResponseEntity.ok().body("Webhook recebido com sucesso!");
    }
}
