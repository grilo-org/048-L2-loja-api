package com.pablo.loja.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Caixa {

    private String idCaixa;

    private int altura;
    private int largura;
    private int comprimento;
    private int volumeRestante;
    
    private List<Produto> produtos = new ArrayList<>();

    public Caixa(String idCaixa, int altura, int largura, int comprimento) {
        this.idCaixa = idCaixa;
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
        this.volumeRestante = altura * largura * comprimento;
    }

    public boolean adicionarProduto(Produto produto) {
        int volumeProduto = produto.getVolume();
        if (volumeProduto <= volumeRestante) {
            produtos.add(produto);
            volumeRestante -= volumeProduto;
            return true;
        }
        return false;
    }

}

