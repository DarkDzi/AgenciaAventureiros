package TP1.example.Controller;

import TP1.example.Domain.Aventureiro;
import TP1.example.Domain.Classe;
import TP1.example.Domain.Companheiro;
import TP1.example.Domain.StatusAventureiro;
import TP1.example.Service.AventureiroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/aventureiros")
public class AventureiroController {
    //TODO Fazer pageamento
    private final AventureiroService service;

    public AventureiroController(AventureiroService service) {
        this.service = service;
    }
    @GetMapping("/filtrar")
    public ResponseEntity<List<Aventureiro>> listarFiltradoComPaginacao(
            @RequestParam(required = false) String classe,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer nivelMin,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        Classe classeEnum = null;
        StatusAventureiro statusEnum = null;

        if (classe != null) {
            try {
                classeEnum = Classe.valueOf(classe.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest()
                        .body(Collections.emptyList());
            }
        }

        if (status != null) {
            try {
                statusEnum = StatusAventureiro.valueOf(status.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest()
                        .body(Collections.emptyList());
            }
        }


        List<Aventureiro> filtrados = service.listarComPaginacao(classeEnum, statusEnum, nivelMin, 0, Integer.MAX_VALUE);

        int total = filtrados.size();
        int totalPages = (int) Math.ceil((double) total / size);

        int fromIndex = Math.min(page * size, total);
        int toIndex = Math.min(fromIndex + size, total);
        List<Aventureiro> pagina = filtrados.subList(fromIndex, toIndex);

        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(total))
                .header("X-Page", String.valueOf(page))
                .header("X-Size", String.valueOf(size))
                .header("X-Total-Pages", String.valueOf(totalPages))
                .body(pagina);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Aventureiro> buscarPorId(@PathVariable Long id) {
        Aventureiro av = service.BuscarPorId(id);
        if (av == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(av);
    }

    @PostMapping
    public ResponseEntity<Aventureiro> registrar(@RequestBody Aventureiro aventureiro) {
        Aventureiro salvo = service.Salvar(aventureiro);
        URI location = URI.create("/aventureiros/" + salvo.getId());
        return ResponseEntity.created(location).body(salvo);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean removido = service.Deletar(id);
        if (!removido) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Aventureiro> atualizar(@PathVariable Long id, @RequestBody Aventureiro novosDados) {
        Aventureiro existente = service.BuscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }


        if (novosDados.getNome() != null) {
            service.AtualizarNome(id, novosDados.getNome());
        }
        if (novosDados.getClasse() != null) {
            service.AtualizarClasse(id, novosDados.getClasse());
        }
        if (novosDados.getNivel() != null) {
            service.AtualizarNivel(id, novosDados.getNivel());
        }

        Aventureiro atualizado = service.BuscarPorId(id);
        return ResponseEntity.ok(atualizado);
    }
    @PatchMapping("/{id}/encerrar")
    public ResponseEntity<Void> encerrar(@PathVariable Long id) {
        boolean feito = service.EncerrarVinculo(id);
        if (!feito) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/{id}/recrutar")
    public void recrutar(@PathVariable Long id) {
        service.RecrutarNovamente(id);
    }
    @PutMapping("/{id}/companheiro")
    public ResponseEntity<Aventureiro> definirCompanheiro(@PathVariable Long id,
                                                          @RequestBody Companheiro companheiro) {
        boolean feito = service.DefinirCompanheiro(id, companheiro);
        if (!feito) {
            return ResponseEntity.notFound().build();
        }
        Aventureiro atualizado = service.BuscarPorId(id);
        return ResponseEntity.ok(atualizado);
    }
    @DeleteMapping("{id}/companheiro")
    public void removercompanheiro(@PathVariable Long id) {
        service.RemoverCompanheiro(id);
    }
}
