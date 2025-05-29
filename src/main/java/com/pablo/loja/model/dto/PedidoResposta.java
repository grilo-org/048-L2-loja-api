package com.pablo.loja.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PedidoResposta {

    @JsonProperty("pedido_id")
    private String pedidoId;

    private List<CaixaResposta> caixas;

    public PedidoResposta(String pedidoId, List<CaixaResposta> caixas) {
        this.pedidoId = pedidoId;
        this.caixas = caixas;
    }

}

