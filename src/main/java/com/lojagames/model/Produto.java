package com.lojagames.model;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name="tb_produtos")

public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo é obrigatório e não pode ser vazio")
    @Size(min = 5,max = 100,message = "O campo título deverá conter no mínimo 5 caracteres e no máximo 100 caracteres")
    private String titulo;

    @Size(min=5,max = 1000,message = "O campo descrição deverá conter no mínimo 5 caracteres e no máximo 100 caracteres")
    private String descricao;


    @NotNull
    private double valor;


    @UpdateTimestamp
    private LocalDateTime data;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if(valor > 0)
        this.valor = valor;
        else
            System.out.println("Valor não pode ser negativo");
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
