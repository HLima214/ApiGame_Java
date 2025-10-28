package com.curso.gameapi.service;

import com.curso.gameapi.dto.GameRequest;
import com.curso.gameapi.dto.GameResponse;
import java.util.List;
import java.util.Optional;


public interface GameService {

    List<GameResponse>listAll();

    Optional<GameResponse> findResponseById(Integer id);


    GameResponse create(GameRequest request);

    Optional<GameResponse> update(Integer id, GameRequest request);

    boolean delete(Integer id);
}
