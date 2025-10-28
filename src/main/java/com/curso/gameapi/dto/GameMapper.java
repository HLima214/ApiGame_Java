package com.curso.gameapi.dto;

import com.curso.gameapi.models.Game;
import java.time.Year;

public class GameMapper {

    public static Game toEntity(GameRequest req) {
        Game g = new Game();
        g.setTitulo(req.titulo());
        g.setEditora(req.editora());
        g.setGenero(req.genero());
        g.setAnoLancamento(req.anoLancamento() != null ? Year.of(req.anoLancamento()) : null);
        return g;
    }

    public static GameResponse toResponse(Game g) {
        Integer ano = g.getAnoLancamento() != null ? g.getAnoLancamento().getValue() : null;
        return new GameResponse(
                g.getIdGame(),
                g.getTitulo(),
                g.getEditora(),
                g.getGenero(),
                ano
        );
    }
}