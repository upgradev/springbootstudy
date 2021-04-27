package br.com.upgrade.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.upgrade.domain.entity.ItemPedido;

public interface ItemsPedido extends JpaRepository<ItemPedido, Integer> {

}
