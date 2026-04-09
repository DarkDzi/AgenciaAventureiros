package TP1.example.Aventura.Controller;

import TP1.example.Aventura.Domain.Aventureiro;
import TP1.example.Aventura.Domain.Classe;
import TP1.example.Aventura.Domain.Companheiro;
import TP1.example.Aventura.Service.AventureiroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/aventureiros")
@RequiredArgsConstructor
public class AventureiroController {

    private final AventureiroService service;

    @GetMapping
    public ResponseEntity<List<Aventureiro>> listarTodos() {
        return ResponseEntity.ok(service.ListarTodos());
    }

    @GetMapping("/classe/{classe}")
    public ResponseEntity<List<Aventureiro>> listarPorClasse(@PathVariable String classe) {
        try {
            Classe classeEnum = Classe.valueOf(classe.trim().toUpperCase());
            return ResponseEntity.ok(service.ListarPorClasse(classeEnum));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
    }

    @GetMapping("/nivel/{nivel}")
    public ResponseEntity<List<Aventureiro>> listarPorNivel(@PathVariable Integer nivel) {
        return ResponseEntity.ok(service.ListarPorNivelMaiorQue(nivel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aventureiro> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.BuscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Aventureiro> registrar(@RequestBody Aventureiro aventureiro) {
        Aventureiro salvo = service.Salvar(aventureiro);
        URI location = URI.create("/aventureiros/" + salvo.getId());
        return ResponseEntity.created(location).body(salvo);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Aventureiro> atualizar(@PathVariable Long id,
                                                 @RequestBody Aventureiro novosDados) {
        if (novosDados.getNome() != null) {
            service.AtualizarNome(id, novosDados.getNome());
        }
        if (novosDados.getClasse() != null) {
            service.AtualizarClasse(id, novosDados.getClasse());
        }
        if (novosDados.getNivel() != null) {
            service.AtualizarNivel(id, novosDados.getNivel());
        }
        return ResponseEntity.ok(service.BuscarPorId(id));
    }

    @PatchMapping("/{id}/encerrar")
    public ResponseEntity<Void> encerrar(@PathVariable Long id) {
        service.EncerrarVinculo(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/recrutar")
    public ResponseEntity<Void> recrutar(@PathVariable Long id) {
        service.RecrutarNovamente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/companheiro")
    public ResponseEntity<Aventureiro> definirCompanheiro(@PathVariable Long id,
                                                          @RequestBody Companheiro companheiro) {
        service.DefinirCompanheiro(id, companheiro);
        return ResponseEntity.ok(service.BuscarPorId(id));
    }

    @DeleteMapping("/{id}/companheiro")
    public ResponseEntity<Void> removerCompanheiro(@PathVariable Long id) {
        service.RemoverCompanheiro(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.Deletar(id);
        return ResponseEntity.noContent().build();
    }
}