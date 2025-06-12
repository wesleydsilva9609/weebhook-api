package com.lojavirtual.web_hooks.dto;

import com.lojavirtual.web_hooks.model.Vendas;

public record DadosDetalhamentoVenda(Long id, Double valor,String status) {
    public DadosDetalhamentoVenda(Vendas vendas) {
        this(vendas.getId(), vendas.getValor(), vendas.getStatus());
    }

}

