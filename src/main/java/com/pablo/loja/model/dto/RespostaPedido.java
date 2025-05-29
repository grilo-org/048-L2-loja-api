package com.pablo.loja.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pablo.loja.model.Caixa;

import lombok.Data;

@Data
public class RespostaPedido {

    @JsonProperty("pedido_id")
    private String idPedido;
    
    private List<Caixa> caixas;

    public RespostaPedido(String idPedido, List<Caixa> caixas) {
        this.idPedido = idPedido;
        this.caixas = caixas;
    }

    // Getters e setters
}

