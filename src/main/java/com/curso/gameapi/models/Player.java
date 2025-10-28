package com.curso.gameapi.models;

import jakarta.persistence.*;

@Entity
public class Player {

    //Propriedades
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPlayer;

    @Column
    private String nome;

    //Indicando Relacionamento
    @ManyToOne
    //Indicando o nome da FK
    @JoinColumn(name = "fav_game")
    private Game gameFav;

    //Construtores
    public Player(String nome, Game gameFav) {
        this.nome = nome;
        this.gameFav = gameFav;
    }

    public Player(){}

    //Getters
    public Integer getIdPlayer() {
        return idPlayer;
    }

    public String getNome() {
        return nome;
    }

    public Game getGameFav() {
        return gameFav;
    }

    //Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setGameFav(Game gameFav) {
        this.gameFav = gameFav;
    }

    //Sobrescrita toString
    @Override
    public String toString() {
        return "Player{" +
                "idPlayer=" + idPlayer +
                ", nome='" + nome + '\'' +
                ", gameFav=" + gameFav +
                '}';
    }
}