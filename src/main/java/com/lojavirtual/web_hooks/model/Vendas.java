package com.lojavirtual.web_hooks.model;

import com.lojavirtual.web_hooks.dto.DadosCadastroVenda;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Vendas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double valor;
    private String status;

    public Vendas(Long id, Double valor) {
        this.id = id;
        this.valor = valor;
        this.status = "Pendente";
    }


    public Vendas(DadosCadastroVenda dadosCadastroVenda) {
        this.id = dadosCadastroVenda.id();
        this.valor = dadosCadastroVenda.valor();
        this.status = "Pendente";
    }


}
