package com.lojagames.repository;

import com.lojagames.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    public List<Categoria> findAllByTipoContainingIgnoreCaseOrderByTipo(@Param("tipo") String tipo);
}
