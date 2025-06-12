package com.lojavirtual.web_hooks.controller;

import com.lojavirtual.web_hooks.dto.WebhookStripeDTO;
import com.lojavirtual.web_hooks.service.VendasService;
import com.lojavirtual.web_hooks.service.WebhookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/webhook")
public class WebHookController {
    @Autowired
    private VendasService vendasService;
    @Autowired
    private WebhookService webhookService;

    @PostMapping("/stripe")
    @Transactional
    public ResponseEntity<String> receberWebhook(HttpServletRequest request) throws IOException {
        return webhookService.receberWebhook(request);
    }

    @PostMapping("/teste")
    public ResponseEntity<String> testewebHook(@RequestBody Map<String, Object> payload){
        return webhookService.testeWeb(payload);
    }
}
