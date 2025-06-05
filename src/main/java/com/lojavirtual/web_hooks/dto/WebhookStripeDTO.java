package com.lojavirtual.web_hooks.dto;

import javax.xml.crypto.Data;
import java.util.Map;

public record WebhookStripeDTO(
        String type,
        Data data) {
    public record Data(
            ObjectData object
    ) {}

    public record ObjectData(
            Map<String, String> metadata
    ) {}
}

