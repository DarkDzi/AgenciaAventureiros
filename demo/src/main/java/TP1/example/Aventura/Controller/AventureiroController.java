package TP1.example.Aventura.Controller;

import TP1.example.Aventura.Domain.Aventureiro;
import TP1.example.Aventura.Domain.Classe;
import TP1.example.Aventura.Domain.Companheiro;
import TP1.example.Aventura.Domain.StatusAventureiro;
import TP1.example.Aventura.Dto.EssencialNomeDto;
import TP1.example.Aventura.Dto.PerfilCompletoDto;
import TP1.example.Aventura.Service.AventureiroService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/aventureiros")
@RequiredArgsConstructor
public class AventureiroController {

    private final AventureiroService service;

    @GetMapping
    public ResponseEntity<Page<Aventureiro>> listarTodos(
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size
                        ){
        return ResponseEntity.ok(service.ListarTodos(page, size));
    }

    @GetMapping("/classe/{classe}")
    public ResponseEntity<Page<Aventureiro>> listarPorClasse(
            @PathVariable String classe,
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size
            ) {
            Classe classeenum = Classe.valueOf(classe);
            return ResponseEntity.ok(service.ListarPorClasse(classeenum,page, size));
    }

    @GetMapping("/nivel_min/{nivel}")
    public ResponseEntity<Page<Aventureiro>> listarPorNivelMin(
            @PathVariable Integer nivel,
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(service.ListarPorNivelMaiorQue(nivel, page, size));

    }


    @GetMapping("/status/{status}")
    public ResponseEntity<Page<Aventureiro>> listarPorStatus(
            @PathVariable StatusAventureiro status,
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size
            ) {
        return ResponseEntity.ok(service.ListarPorStatus(status,page, size));
    }
    @GetMapping("/nome/{nome}")
    public ResponseEntity<Page<EssencialNomeDto>> listarPorNome(
            @PathVariable String nome,
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size,
            @RequestParam (defaultValue = "true") boolean crescente
    )
    {
        return ResponseEntity.ok(service.ListarPorNome(nome, page, size,crescente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aventureiro> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.BuscarPorId(id));
    }
    @GetMapping("/perfil/{id}")
    public ResponseEntity<PerfilCompletoDto> buscarPerfilporId(@PathVariable Long id) {
        return ResponseEntity.ok(service.ListarPerfilCompleto(id));
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