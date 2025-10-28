package com.curso.gameapi.dto;

import com.curso.gameapi.models.Player;

public class PlayerMapper {
    public static Player toEntity(PlayerRequest req){
        Player p = new Player();
        p.setNome(req.nome());
        p.setGameFav(req.gameFav());
        return p;
    }

    public static PlayerResponse toResponse(Player p){
        return new PlayerResponse(
                p.getIdPlayer(),
                p.getNome(),
                p.getGameFav()
        );
    }
}
