package com.lojavirtual.web_hooks.controller;

import com.lojavirtual.web_hooks.dto.DadosCadastroVenda;
import com.lojavirtual.web_hooks.dto.DadosDetalhamentoVenda;
import com.lojavirtual.web_hooks.repository.repositoryVendas;
import com.lojavirtual.web_hooks.service.VendasService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/vendas")
public class VendasController {
    @Autowired
    private VendasService vendasService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastroVenda(@RequestBody DadosCadastroVenda dadosCadastroVenda, UriComponentsBuilder uriComponentsBuilder){
        return vendasService.cadastroVenda(dadosCadastroVenda,uriComponentsBuilder) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity BuscarVendasPorId(@PathVariable Long id){
        return vendasService.BuscaPorId(id);
    }


}
