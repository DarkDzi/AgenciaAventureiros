package TP1.example.Aventura.Controller;

import TP1.example.Aventura.Domain.ParticipacaoMissao;
import TP1.example.Aventura.Domain.PapelMissao;
import TP1.example.Aventura.Dto.ParticipacaoTudo;
import TP1.example.Aventura.Service.ParticipacaoMissaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
        return participacaoMissaoService.listarTodos(page, size);
    }

    @GetMapping("/missao/{missaoId}")
    public Page<ParticipacaoTudo> listarPorMissao(
            @PathVariable Long missaoId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
            ) {
        return participacaoMissaoService.listarPorMissao(missaoId, page, size);
    }

    @GetMapping("/aventureiro/{aventureiroId}")
    public Page<ParticipacaoTudo> listarPorAventureiro(
            @PathVariable Long aventureiroId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size


    ) {
        return participacaoMissaoService.listarPorAventureiro(aventureiroId, page ,size);
    }

    @PostMapping
    public ParticipacaoMissao salvar(@RequestBody ParticipacaoMissao participacao) {
        return participacaoMissaoService.salvar(participacao);
    }

    @DeleteMapping("/{missaoId}/{aventureiroId}")
    public void deletar(
            @PathVariable Long missaoId,
            @PathVariable Long aventureiroId) {
        participacaoMissaoService.deletar(missaoId, aventureiroId);
    }

    @PatchMapping("/{missaoId}/{aventureiroId}/papel")
    public void atualizarPapel(
            @PathVariable Long missaoId,
            @PathVariable Long aventureiroId,
            @RequestParam PapelMissao papel) {
        participacaoMissaoService.atualizarPapel(missaoId, aventureiroId, papel);
    }

    @PatchMapping("/{missaoId}/{aventureiroId}/mvp")
    public void definirMvp(
            @PathVariable Long missaoId,
            @PathVariable Long aventureiroId) {
        participacaoMissaoService.definirMvp(missaoId, aventureiroId);
    }

    @PatchMapping("/{missaoId}/{aventureiroId}/recompensa")
    public void definirRecompensa(
            @PathVariable Long missaoId,
            @PathVariable Long aventureiroId,
            @RequestParam Integer ouro) {
        participacaoMissaoService.definirRecompensa(missaoId, aventureiroId, ouro);
    }
}