package com.curso.gameapi.service;

import com.curso.gameapi.dto.PlayerMapper;
import com.curso.gameapi.dto.PlayerRequest;
import com.curso.gameapi.dto.PlayerResponse;
import com.curso.gameapi.models.Player;
import com.curso.gameapi.repository.PlayerRepository;
import com.curso.gameapi.service.PlayerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class PlayerServiceImpl implements PlayerService{

    private final PlayerRepository repository;

    public PlayerServiceImpl(PlayerRepository repository){

        this.repository = repository;
    }

    @Override
    public List<PlayerResponse>listAll(){

        return repository.findAll().stream()
                .map(PlayerMapper::toResponse)
                .collect(toList());
    }


    @Override
    public Optional<PlayerResponse> findResponseById(Integer id){

        return repository.findById(id)
                .map(PlayerMapper::toResponse);
    }

    @Override
    @Transactional
    public PlayerResponse create(PlayerRequest request){
        Player entity = PlayerMapper.toEntity(request);
        Player saved = repository.save(entity);
        return PlayerMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public Optional<PlayerResponse> update(Integer id, PlayerRequest request){


        return repository.findById(id)
                .map(player -> {
                    player.setNome(request.nome());
                    player.setGameFav(request.gameFav());
                    Player updated = repository.save(player);
                    return PlayerMapper.toResponse(updated);
                });
    }

    @Override
    @Transactional
    public boolean delete(Integer id){
        return repository.findById(id)
                .map(player -> {
                    repository.delete(player);
                    return true;
                })
                .orElse(false);
    }



}
