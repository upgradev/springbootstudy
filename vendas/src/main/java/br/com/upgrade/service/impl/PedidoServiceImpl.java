package br.com.upgrade.service.impl;

import br.com.upgrade.domain.entity.Cliente;
import br.com.upgrade.domain.entity.ItemPedido;
import br.com.upgrade.domain.entity.Pedido;
import br.com.upgrade.domain.entity.Produto;
import br.com.upgrade.domain.enums.StatusPedido;
import br.com.upgrade.domain.repository.Clientes;
import br.com.upgrade.domain.repository.ItemsPedido;
import br.com.upgrade.domain.repository.Pedidos;
import br.com.upgrade.domain.repository.Produtos;
import br.com.upgrade.exception.PedidoNaoEncontradoException;
import br.com.upgrade.exception.RegraNegocioException;
import br.com.upgrade.rest.dto.ItemPedidoDTO;
import br.com.upgrade.rest.dto.PedidoDTO;
import br.com.upgrade.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItemsPedido itensPedidoRepository;


    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository.findById(idCliente).orElseThrow(() -> new RegraNegocioException("Código de cliente inválido"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itensPedidos = converterItens(pedido, dto.getItens());
        repository.save(pedido);
        itensPedidoRepository.saveAll(itensPedidos);
        pedido.setItens(itensPedidos);
        return pedido;

    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchIntens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        repository.findById(id).map(pedido -> {
            pedido.setStatus(statusPedido);
            return repository.save(pedido);
        }).orElseThrow(() -> new PedidoNaoEncontradoException());
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens) {
        if (itens.isEmpty()) {
            throw new RegraNegocioException("Não é possivel realizar um pedido sem itens");
        }

        return itens.stream().map(dto -> {
            Integer idproduto = dto.getProduto();
            Produto produto = produtosRepository.findById(idproduto).orElseThrow(() ->
                    new RegraNegocioException("Produto invalido: " + idproduto));
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setQuantidade(dto.getQuantidade());
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            return itemPedido;
        }).collect(Collectors.toList());
    }
}
