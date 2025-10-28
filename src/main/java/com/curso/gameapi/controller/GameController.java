package com.curso.gameapi.controller;

import com.curso.gameapi.dto.GameRequest;
import com.curso.gameapi.dto.GameResponse;
import com.curso.gameapi.service.GameService; // Novo import para o serviço
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/games")
@Tag(name = "Games", description = "CRUD de Games")
public class GameController {

    // A controller agora injeta e utiliza o GameService
    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    // GET (lista todos)
    @Operation(summary = "Lista todos os games")
    @GetMapping
    public List<GameResponse> list() {
        // Delega a lógica de listagem para o serviço
        return service.listAll();
    }

    // GET (busca por id)
    @Operation(summary = "Busca um game por ID")
    @GetMapping("/{id}")
    public ResponseEntity<GameResponse> findById(@PathVariable Integer id) {
        // Delega a busca ao serviço e trata o Optional
        return service.findResponseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE
    @Operation(summary = "Cria um novo game")
    @PostMapping
    public ResponseEntity<GameResponse> create(@Valid @RequestBody GameRequest request) {
        // Delega a criação ao serviço
        GameResponse savedResponse = service.create(request);

        // A controller cria o cabeçalho 'Location'
        URI location = URI.create("/api/games/" + savedResponse.idGame());

        return ResponseEntity.created(location).body(savedResponse);
    }

    // UPDATE
    @Operation(summary = "Atualiza um game existente")
    @PutMapping("/{id}")
    public ResponseEntity<GameResponse> update(
            @PathVariable Integer id,
            @Valid @RequestBody GameRequest request) {
        // Delega a atualização ao serviço e trata o Optional
        return service.update(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @Operation(summary = "Deleta um game por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        // Delega a deleção ao serviço e constrói a resposta HTTP (204 No Content ou 404 Not Found)
        boolean deleted = service.delete(id);
        return deleted ? ResponseEntity.noContent().<Void>build() : ResponseEntity.notFound().build();
    }
}
