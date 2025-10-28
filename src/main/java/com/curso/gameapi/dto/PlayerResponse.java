package com.curso.gameapi.dto;

import com.curso.gameapi.models.Game;
import com.curso.gameapi.models.Player;

public record PlayerResponse (
        Integer idPlayer,
        String nome,
        Game gameFav
){ }
