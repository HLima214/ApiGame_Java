package com.curso.gameapi.dto;

import com.curso.gameapi.models.Game;
import jakarta.validation.constraints.*;

public record PlayerRequest (
    @NotBlank @Size(max = 100) String nome,
    @Size(max = 80) Game gameFav
){}
