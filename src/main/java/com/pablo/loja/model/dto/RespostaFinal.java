package com.pablo.loja.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class RespostaFinal {

    private List<PedidoResposta> pedidos;

    public RespostaFinal(List<PedidoResposta> pedidos) {
        this.pedidos = pedidos;
    }

}

