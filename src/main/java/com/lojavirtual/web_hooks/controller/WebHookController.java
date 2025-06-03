package com.lojavirtual.web_hooks.controller;

import com.lojavirtual.web_hooks.dto.WebhookStripeDTO;
import com.lojavirtual.web_hooks.service.VendasService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/webhook")
public class WebHookController {
    @Autowired
    private VendasService vendasService;

    @PostMapping("/stripe")
    @Transactional
    public ResponseEntity<String> receberWebhook(@RequestBody WebhookStripeDTO payload) {
        var vendaId = payload.data().object().metadata().get("venda");
        System.out.println("webhook recebido " + vendaId);
        vendasService.confirmarPagamento(vendaId);

        return ResponseEntity.ok().body("Webhook recebido com sucesso!");
    }
}
