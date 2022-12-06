package com.lojagames.controller;


import com.lojagames.model.Produto;
import com.lojagames.repository.CategoriaRepository;
import com.lojagames.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Produto>> getAll() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id){
        return produtoRepository.findById(id)
                .map(res -> ResponseEntity.ok(res))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("descricao/{descricao}")
    public ResponseEntity<List<Produto>> getAll(@PathVariable String descricao) {
        try {
            return ResponseEntity.ok(produtoRepository.findAllByDescricaoContainsIgnoreCase(descricao));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("preco-maior/{valor}")
    public ResponseEntity<List<Produto>> getAllMaior(@PathVariable Double valor) {
        try {
            return ResponseEntity.ok(produtoRepository.findAllByValorGreaterThanOrderByValor(valor));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("preco-menor/{valor}")
    public ResponseEntity<List<Produto>> getAllMenor(@PathVariable Double valor) {
        try {
            return ResponseEntity.ok(produtoRepository.findAllByValorIsLessThanOrderByValor(valor));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PostMapping
    public ResponseEntity<Produto> postPostagem(@Valid @RequestBody Produto produto) {

        if (categoriaRepository.existsById(produto.getCategoria().getId())) //chec id do tema dentro da postagem
            return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping
    public ResponseEntity<Produto> putPostagem(@Valid @RequestBody Produto produto) {

        if (produtoRepository.existsById(produto.getId())) {//tem id da produto checa o tema
            if (categoriaRepository.existsById(produto.getCategoria().getId())) // tem o categoria ele grava embaixo
                return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id){
        try {
            produtoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
