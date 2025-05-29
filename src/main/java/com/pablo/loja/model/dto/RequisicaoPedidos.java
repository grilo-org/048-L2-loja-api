package com.pablo.loja.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pablo.loja.model.Pedido;

public class RequisicaoPedidos {

    @JsonProperty("pedidos")
    private List<Pedido> pedidos;

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}

