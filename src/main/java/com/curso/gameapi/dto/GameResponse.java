package com.curso.gameapi.dto;

public record GameResponse(
        Integer idGame,
        String titulo,
        String editora,
        String genero,
        Integer anoLancamento
) { }