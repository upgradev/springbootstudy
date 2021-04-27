package br.com.upgrade.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.upgrade.domain.entity.Produto;

public interface Produtos extends JpaRepository<Produto, Integer> {

}
