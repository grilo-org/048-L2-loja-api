package com.pablo.loja.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CaixaResposta {

    @JsonProperty("caixa_id")
    private String caixaId;

    private List<String> produtos;

    public CaixaResposta(String caixaId, List<String> produtos) {
        this.caixaId = caixaId;
        this.produtos = produtos;
    }

}

