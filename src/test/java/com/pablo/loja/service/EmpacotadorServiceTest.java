package com.pablo.loja.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.pablo.loja.model.Pedido;
import com.pablo.loja.model.Produto;
import com.pablo.loja.model.dto.PedidoResposta;
import com.pablo.loja.model.dto.RespostaFinal;

@SpringBootTest
class EmpacotadorServiceTest {

    private final EmpacotadorService service = new EmpacotadorService();

    @Test
    void testEmpacotamentoSimples() {
        Produto p1 = new Produto();
        p1.setIdProduto("Produto A");
        p1.setAltura(10);
        p1.setLargura(10);
        p1.setComprimento(10);

        Pedido pedido = new Pedido();
        pedido.setIdPedido("pedido1");
        pedido.setProdutos(List.of(p1));

        RespostaFinal resposta = service.processarPedidos(List.of(pedido));

        Assertions.assertNotNull(resposta);
        Assertions.assertEquals(1, resposta.getPedidos().size());
        PedidoResposta pedidoResp = resposta.getPedidos().get(0);
        Assertions.assertEquals("pedido1", pedidoResp.getPedidoId());
        Assertions.assertFalse(pedidoResp.getCaixas().isEmpty());
        Assertions.assertTrue(
            pedidoResp.getCaixas().get(0).getProdutos().contains("Produto A")
        );
    }

    @Test
    void testEmpacotamentoComMultiplosProdutosMesmaCaixa() {
        Produto p1 = new Produto(); p1.setIdProduto("produto1"); p1.setAltura(10); p1.setLargura(10); p1.setComprimento(10);
        Produto p2 = new Produto(); p2.setIdProduto("produto2"); p2.setAltura(10); p2.setLargura(10); p2.setComprimento(10);

        Pedido pedido = new Pedido();
        pedido.setIdPedido("pedido1");
        pedido.setProdutos(List.of(p1, p2));

        RespostaFinal resposta = service.processarPedidos(List.of(pedido));

        PedidoResposta pedidoResp = resposta.getPedidos().get(0);
        Assertions.assertEquals("pedido1", pedidoResp.getPedidoId());
        Assertions.assertTrue(pedidoResp.getCaixas().size() >= 1);
        long totalProdutos = pedidoResp.getCaixas().stream()
            .flatMap(c -> c.getProdutos().stream()).count();
        Assertions.assertEquals(2, totalProdutos);
    }

    @Test
    void testEmpacotamentoComProdutosGrandesMultiplasCaixas() {
        Produto p1 = new Produto(); p1.setIdProduto("produto1"); p1.setAltura(40); p1.setLargura(40); p1.setComprimento(40);
        Produto p2 = new Produto(); p2.setIdProduto("produto2"); p2.setAltura(40); p2.setLargura(40); p2.setComprimento(40);
        Produto p3 = new Produto(); p3.setIdProduto("produto3"); p3.setAltura(40); p3.setLargura(40); p3.setComprimento(40);

        Pedido pedido = new Pedido();
        pedido.setIdPedido("pedido1");
        pedido.setProdutos(List.of(p1, p2, p3));

        RespostaFinal resposta = service.processarPedidos(List.of(pedido));

        PedidoResposta pedidoResp = resposta.getPedidos().get(0);
        Assertions.assertEquals("pedido1", pedidoResp.getPedidoId());
        Assertions.assertTrue(pedidoResp.getCaixas().size() >= 2);
    }

    @Test
    void testVariosPedidosComEmpacotamentoIndependente() {
        Produto p1 = new Produto(); p1.setIdProduto("produto1"); p1.setAltura(10); p1.setLargura(10); p1.setComprimento(10);
        Produto p2 = new Produto(); p2.setIdProduto("produto2"); p2.setAltura(30); p2.setLargura(30); p2.setComprimento(30);

        Pedido pedido1 = new Pedido(); pedido1.setIdPedido("pedido1"); pedido1.setProdutos(List.of(p1));
        Pedido pedido2 = new Pedido(); pedido2.setIdPedido("pedido2"); pedido2.setProdutos(List.of(p2));

        RespostaFinal resposta = service.processarPedidos(List.of(pedido1, pedido2));

        Assertions.assertEquals(2, resposta.getPedidos().size());
        Assertions.assertEquals("pedido1", resposta.getPedidos().get(0).getPedidoId());
        Assertions.assertEquals("pedido2", resposta.getPedidos().get(1).getPedidoId());
    }
}