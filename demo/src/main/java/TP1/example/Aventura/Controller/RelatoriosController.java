package TP1.example.Aventura.Controller;

import TP1.example.Aventura.Domain.StatusMissao;
import TP1.example.Aventura.Dto.RankingAventureiroDto;
import TP1.example.Aventura.Dto.RelatorioMissaoDto;
import TP1.example.Aventura.Service.RelatoriosService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/relatorios")
@RequiredArgsConstructor
public class RelatoriosController {

    private final RelatoriosService relatoriosService;

    @GetMapping("/ranking")
    public List<RankingAventureiroDto> gerarRankingParticipacao(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim,
            @RequestParam(required = false) StatusMissao statusMissao) {
        return relatoriosService.gerarRankingParticipacao(inicio, fim, statusMissao);
    }

    @GetMapping("/missoes")
    public List<RelatorioMissaoDto> gerarRelatorioMissoes(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return relatoriosService.gerarRelatorioMissoes(inicio, fim);
    }
}