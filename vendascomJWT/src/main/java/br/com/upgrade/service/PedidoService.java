package br.com.upgrade.service;

import br.com.upgrade.domain.entity.Pedido;
import br.com.upgrade.domain.enums.StatusPedido;
import br.com.upgrade.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
