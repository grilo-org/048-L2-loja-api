package com.pablo.loja.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pablo.loja.model.Caixa;
import com.pablo.loja.model.Pedido;
import com.pablo.loja.model.Produto;
import com.pablo.loja.model.dto.CaixaResposta;
import com.pablo.loja.model.dto.PedidoResposta;
import com.pablo.loja.model.dto.RespostaFinal;

@Service
public class EmpacotadorService {

    private final List<Caixa> tiposCaixa = List.of(
            new Caixa("Caixa 1", 30, 40, 80),
            new Caixa("Caixa 2", 80, 50, 40),
            new Caixa("Caixa 3", 50, 80, 60));

    public RespostaFinal processarPedidos(List<Pedido> pedidos) {
        List<PedidoResposta> respostaPedidos = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            List<CaixaResposta> caixasResposta = new ArrayList<>();
            List<Caixa> caixasUsadas = new ArrayList<>();

            for (Produto produto : pedido.getProdutos()) {
                boolean encaixado = false;
                for (Caixa caixa : caixasUsadas) {
                    if (caixa.adicionarProduto(produto)) {
                        encaixado = true;
                        break;
                    }
                }

                if (!encaixado) {
                    Caixa novaCaixa = tiposCaixa.stream()
                                                .filter(c -> c.getVolumeRestante() >= produto.getVolume())
                                                .sorted(Comparator.comparingInt(Caixa::getVolumeRestante))
                                                .findFirst()
                                                .map(c -> new Caixa(c.getIdCaixa(), c.getAltura(), c.getLargura(), c.getComprimento()))
                                                .orElseThrow();

                    novaCaixa.adicionarProduto(produto);
                    caixasUsadas.add(novaCaixa);
                }
            }

            for (Caixa cx : caixasUsadas) {
                List<String> nomesProdutos = cx.getProdutos().stream().map(Produto::getIdProduto).toList();

                caixasResposta.add(new CaixaResposta(cx.getIdCaixa(), nomesProdutos));
            }

            respostaPedidos.add(new PedidoResposta(pedido.getIdPedido(), caixasResposta));
        }

        return new RespostaFinal(respostaPedidos);
    }

}
