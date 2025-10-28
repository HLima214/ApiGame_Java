package com.curso.gameapi.controller;

import com.curso.gameapi.dto.PlayerRequest;
import com.curso.gameapi.dto.PlayerResponse;
import com.curso.gameapi.service.PlayerService; // Novo import para o serviço
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/players")
@Tag(name= "Players", description = "CRUD de Players")
public class PlayerController {

    private final PlayerService service;

    public PlayerController(PlayerService service){
        this.service = service;
    }


    @Operation(summary = "Lista todos os players")
    @GetMapping
    public List<PlayerResponse> list(){

        return service.listAll();
    }


    @Operation(summary = "Busca um player por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponse> findById(@PathVariable Integer id) {

        return service.findResponseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE
    @Operation(summary = "Cria um novo player")
    @PostMapping
    public ResponseEntity<PlayerResponse> create(@Valid @RequestBody PlayerRequest request){

        PlayerResponse savedResponse = service.create(request);

        URI location = URI.create("/api/players/" + savedResponse.idPlayer());

        return ResponseEntity.created(location).body(savedResponse);
    }

    // UPDATE
    @Operation(summary = "Atualiza um player existente")
    @PutMapping("/{id}")
    public ResponseEntity<PlayerResponse> update(
            @PathVariable Integer id,
            @Valid @RequestBody PlayerRequest request){
        return service.update(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @Operation(summary = "Deleta um player por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        boolean deleted = service.delete(id);
        return deleted ? ResponseEntity.noContent().<Void>build() : ResponseEntity.notFound().build();
    }
}
