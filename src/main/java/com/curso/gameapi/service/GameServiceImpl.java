package com.curso.gameapi.service;

import com.curso.gameapi.dto.GameMapper;
import com.curso.gameapi.dto.GameRequest;
import com.curso.gameapi.dto.GameResponse;
import com.curso.gameapi.models.Game;
import com.curso.gameapi.repository.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository repository;


    public GameServiceImpl(GameRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<GameResponse> listAll() {
        return repository.findAll().stream()
                .map(GameMapper::toResponse)
                .collect(toList());
    }

    @Override
    public Optional<GameResponse> findResponseById(Integer id) {
        return repository.findById(id)
                .map(GameMapper::toResponse);
    }


    @Override
    @Transactional
    public GameResponse create(GameRequest request) {
        Game entity = GameMapper.toEntity(request);
        Game saved = repository.save(entity);
        return GameMapper.toResponse(saved);
    }


    @Override
    @Transactional
    public Optional<GameResponse> update(Integer id, GameRequest request) {
        return repository.findById(id)
                .map(game -> {
                    // Atualiza os campos da entidade Game
                    game.setTitulo(request.titulo());
                    game.setEditora(request.editora());
                    game.setGenero(request.genero());
                    // Converte o ano inteiro para Year
                    game.setAnoLancamento(Year.of(request.anoLancamento()));

                    Game updated = repository.save(game);
                    return GameMapper.toResponse(updated);
                });
    }


    @Override
    @Transactional
    public boolean delete(Integer id) {
        return repository.findById(id)
                .map(game -> {
                    repository.delete(game);
                    return true;
                })
                .orElse(false);
    }
}
