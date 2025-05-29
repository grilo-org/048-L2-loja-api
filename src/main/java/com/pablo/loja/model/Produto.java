package com.pablo.loja.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Produto {

    @JsonProperty("produto_id")
    private String idProduto;
    private int altura;
    private int largura;
    private int comprimento;

    public int getVolume() {
        return altura * largura * comprimento;
    }

}
