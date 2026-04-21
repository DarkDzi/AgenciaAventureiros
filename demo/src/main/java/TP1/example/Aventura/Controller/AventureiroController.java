package TP1.example.Aventura.Controller;

import TP1.example.Aventura.Domain.Aventureiro;
import TP1.example.Aventura.Domain.Classe;
import TP1.example.Aventura.Domain.Companheiro;
import TP1.example.Aventura.Domain.StatusAventureiro;
import TP1.example.Aventura.Dto.*;
import TP1.example.Aventura.Service.AventureiroService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/aventureiros")
@RequiredArgsConstructor
public class AventureiroController {

    private final AventureiroService service;

    @GetMapping
    public ResponseEntity<Page<AventureiroTudo>> listarTodos(
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size
                        ){
        return ResponseEntity.ok(service.listarTodos(page, size));
    }

    @GetMapping("/classe/{classe}")
    public ResponseEntity<Page<AventureiroTudo>> listarPorClasse(
            @PathVariable String classe,
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size
            ) {
            Classe classeenum = Classe.valueOf(classe.toUpperCase());
            return ResponseEntity.ok(service.listarPorClasse(classeenum,page, size));
    }

    @GetMapping("/nivel_min/{nivel}")
    public ResponseEntity<Page<AventureiroTudo>> listarPorNivelMin(
            @PathVariable Integer nivel,
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(service.listarPorNivelMaiorQue(nivel, page, size));

    }


    @GetMapping("/status/{status}")
    public ResponseEntity<Page<AventureiroTudo>> listarPorStatus(
            @PathVariable String status,
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size
            ) {
        StatusAventureiro statuseenum = StatusAventureiro.valueOf(status.toUpperCase());
        return ResponseEntity.ok(service.listarPorStatus(statuseenum,page, size));
    }
    @GetMapping("/nome/{nome}")
    public ResponseEntity<Page<EssencialNomeDto>> listarPorNome(
            @PathVariable String nome,
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size,
            @RequestParam (defaultValue = "true") boolean crescente
    )
    {
        return ResponseEntity.ok(service.listarPorNome(nome, page, size,crescente));
    }
    @GetMapping("/perfil/{id}")
    public ResponseEntity<PerfilCompletoDto> buscarPerfilporId(@PathVariable Long id) {
        return ResponseEntity.ok(service.listarPerfilCompleto(id));
    }

    @PostMapping
    public ResponseEntity<AventureiroTudo> registrar(@RequestBody AventureiroRegistroDto dto) {
        Aventureiro salvo = service.salvar(dto);

        AventureiroTudo response = new AventureiroTudo(
                salvo.getId(),
                salvo.getOrganizacao().getId(),
                salvo.getUsuarioResponsavel().getNome(),
                salvo.getNome(),
                salvo.getClasse(),
                salvo.getNivel(),
                salvo.getStatus(),
                salvo.getCompanheiro() != null ? salvo.getCompanheiro().getEspecie().name() : null,
                salvo.getCriadoem().toString(),
                salvo.getAtualizadoem().toString()
        );
        URI location = URI.create("/aventureiros/" + salvo.getId());
        return ResponseEntity.created(location).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AventureiroTudo> atualizar(@PathVariable Long id,
                                                     @RequestBody AventureiroAtualizarDto dto) {

        if (dto.getNome() != null) service.atualizarNome(id, dto.getNome());
        if (dto.getClasse() != null) service.atualizarClasse(id, dto.getClasse());
        if (dto.getNivel() != null) service.atualizarNivel(id, dto.getNivel());

        return ResponseEntity.ok(service.buscarTudoPorId(id));
    }


    @PatchMapping("/{id}/encerrar")
    public ResponseEntity<Void> encerrar(@PathVariable Long id) {
        service.encerrarVinculo(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/recrutar")
    public ResponseEntity<Void> recrutar(@PathVariable Long id) {
        service.recrutarNovamente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/companheiro")
    public ResponseEntity<AventureiroTudo> definirCompanheiro(@PathVariable Long id,
                                                              @RequestBody CompanheiroRegistroDto dto) {
        service.definirCompanheiro(id, dto);
        return ResponseEntity.ok(service.buscarTudoPorId(id));
    }

    @DeleteMapping("/{id}/companheiro")
    public ResponseEntity<Void> removerCompanheiro(@PathVariable Long id) {
        service.removerCompanheiro(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}