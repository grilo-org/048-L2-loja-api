package com.pablo.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pablo.loja.model.dto.RequisicaoPedidos;
import com.pablo.loja.model.dto.RespostaFinal;
import com.pablo.loja.service.EmpacotadorService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/empacotar")
@Tag(name = "Loja", description = "Operações para empacotar pedidos em caixas")
public class EmpacotadorController {

    @Autowired
    private EmpacotadorService service;

    @PostMapping
    public RespostaFinal empacotar(@RequestBody RequisicaoPedidos requisicao) {
        return service.processarPedidos(requisicao.getPedidos());
    }
}

