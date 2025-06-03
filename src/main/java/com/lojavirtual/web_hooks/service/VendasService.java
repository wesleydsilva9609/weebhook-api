package com.lojavirtual.web_hooks.service;

import com.lojavirtual.web_hooks.dto.DadosCadastroVenda;
import com.lojavirtual.web_hooks.dto.DadosDetalhamentoVenda;
import com.lojavirtual.web_hooks.model.Vendas;
import com.lojavirtual.web_hooks.repository.repositoryVendas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Service
public class VendasService {
    @Autowired
    private repositoryVendas repositoryVendas;


    public ResponseEntity cadastroVenda(DadosCadastroVenda dadosCadastroVenda, UriComponentsBuilder uriComponentsBuilder) {
        var venda = new Vendas(dadosCadastroVenda);
        var url  = uriComponentsBuilder.path("/vendas").build().toUri();
        repositoryVendas.save(venda);
        return ResponseEntity.created(url).body(new DadosDetalhamentoVenda(venda));
    }

    public ResponseEntity BuscaPorId(Long id) {
        var venda = repositoryVendas.getReferenceById(id);
        return ResponseEntity.ok().body(new DadosDetalhamentoVenda(venda));
    }

    public void confirmarPagamento(String vendaId){
        Optional<Vendas> vendasOpt = repositoryVendas.findById(Long.parseLong(vendaId));
        vendasOpt.ifPresent(vendas -> {
            vendas.setStatus("PAGO");
            repositoryVendas.save(vendas);
            System.out.println("Venda " + vendaId + " marcada como PAGA ");
        });
    }
}
