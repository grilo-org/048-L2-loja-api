package com.pablo.loja.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Pedido {

    @JsonProperty("pedido_id")
    private String idPedido;
    
    private List<Produto> produtos;

}

