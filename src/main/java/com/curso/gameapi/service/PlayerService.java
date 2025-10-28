package com.curso.gameapi.service;

import com.curso.gameapi.dto.PlayerResponse;
import com.curso.gameapi.dto.PlayerRequest;
import java.util.List;
import java.util.Optional;

public interface PlayerService {


    List<PlayerResponse> listAll();

    Optional<PlayerResponse> findResponseById(Integer id);

    PlayerResponse create(PlayerRequest request);

    Optional<PlayerResponse> update(Integer id, PlayerRequest request);

    boolean delete (Integer id);
}
