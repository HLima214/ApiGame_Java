package com.curso.gameapi.models;

import jakarta.persistence.*;

import java.time.Year;

//Anotação para indicar a classe como uma entidade
@Entity
public class Game {

    //Propriedades
    //Indicando a chave primaria
    @Id
    //Indicando auto geração
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGame;

    @Column
    private String titulo;

    @Column
    private String editora;

    @Column
    private String genero;

    @Column
    private Year anoLancamento;

    //Construtores
    public Game(String titulo, String editora, String genero, Year anoLancamento) {
        this.titulo = titulo;
        this.editora = editora;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
    }

    public Game(){} //Construtor Padrão

    //Getters
    public Integer getIdGame() {
        return idGame;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEditora() {
        return editora;
    }

    public String getGenero() {
        return genero;
    }

    public Year getAnoLancamento() {
        return anoLancamento;
    }

    //Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setAnoLancamento(Year anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    //Métod0 sobrescrito toString
    @Override
    public String toString() {
        return "Game{" +
                "idGame=" + idGame +
                ", titulo='" + titulo + '\'' +
                ", editora='" + editora + '\'' +
                ", genero='" + genero + '\'' +
                ", anoLancamento=" + anoLancamento +
                '}';
    }
}
