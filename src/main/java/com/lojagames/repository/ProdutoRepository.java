package com.lojagames.repository;

import com.lojagames.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {

    public List<Produto> findAllByDescricaoContainsIgnoreCase(@Param("descricao") String descricao);

    public List<Produto> findAllByValorGreaterThanOrderByValor(Double valorMaior);

    public List<Produto> findAllByValorIsLessThanOrderByValor(Double valor);



}