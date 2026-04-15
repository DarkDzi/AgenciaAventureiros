package TP1.example.Aventura.Controller;

import TP1.example.Aventura.Domain.ParticipacaoMissao;
import TP1.example.Aventura.Domain.PapelMissao;
import TP1.example.Aventura.Dto.ParticipacaoTudo;
import TP1.example.Aventura.Service.ParticipacaoMissaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participacaomissao")
@RequiredArgsConstructor
public class ParticipacaoMissaoController {

    private final ParticipacaoMissaoService participacaoMissaoService;
    @GetMapping
    public Page<ParticipacaoTudo> ListarTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return participacaoMissaoService.ListarTodos(page, size);
    }

    @GetMapping("/missao/{missaoId}")
    public Page<ParticipacaoTudo> listarPorMissao(
            @PathVariable Long missaoId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
            ) {
        return participacaoMissaoService.ListarPorMissao(missaoId, page, size);
    }

    @GetMapping("/aventureiro/{aventureiroId}")
    public Page<ParticipacaoTudo> listarPorAventureiro(
            @PathVariable Long aventureiroId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size


    ) {
        return participacaoMissaoService.ListarPorAventureiro(aventureiroId, page ,size);
    }

    @PostMapping
    public ParticipacaoMissao salvar(@RequestBody ParticipacaoMissao participacao) {
        return participacaoMissaoService.Salvar(participacao);
    }

    @DeleteMapping("/{missaoId}/{aventureiroId}")
    public void deletar(
            @PathVariable Long missaoId,
            @PathVariable Long aventureiroId) {
        participacaoMissaoService.Deletar(missaoId, aventureiroId);
    }

    @PatchMapping("/{missaoId}/{aventureiroId}/papel")
    public void atualizarPapel(
            @PathVariable Long missaoId,
            @PathVariable Long aventureiroId,
            @RequestParam PapelMissao papel) {
        participacaoMissaoService.AtualizarPapel(missaoId, aventureiroId, papel);
    }

    @PatchMapping("/{missaoId}/{aventureiroId}/mvp")
    public void definirMvp(
            @PathVariable Long missaoId,
            @PathVariable Long aventureiroId) {
        participacaoMissaoService.DefinirMvp(missaoId, aventureiroId);
    }

    @PatchMapping("/{missaoId}/{aventureiroId}/recompensa")
    public void definirRecompensa(
            @PathVariable Long missaoId,
            @PathVariable Long aventureiroId,
            @RequestParam Integer ouro) {
        participacaoMissaoService.DefinirRecompensa(missaoId, aventureiroId, ouro);
    }
}