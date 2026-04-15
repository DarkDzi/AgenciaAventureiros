package TP1.example.Aventura.Service;

import TP1.example.Aventura.Domain.Missao;
import TP1.example.Aventura.Domain.ParticipacaoMissao;
import TP1.example.Aventura.Domain.StatusMissao;
import TP1.example.Aventura.Dto.RankingAventureiroDto;
import TP1.example.Aventura.Dto.RelatorioMissaoDto;
import TP1.example.Aventura.Repository.AventureiroRepository;
import TP1.example.Aventura.Repository.MissaoRepository;
import TP1.example.Aventura.Repository.ParticipacaoMissaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelatoriosService {
    private final ParticipacaoMissaoRepository participacaoMissaoRepository;
    private final MissaoRepository missaoRepository;

    public List<RankingAventureiroDto> gerarRankingParticipacao(Timestamp inicio, Timestamp fim, StatusMissao status) {
        List<ParticipacaoMissao> participacoes = participacaoMissaoRepository.buscarParaRanking(inicio, fim, status);
        return construirRanking(participacoes);
    }
    private List<RankingAventureiroDto> construirRanking(List<ParticipacaoMissao> participacoes) {
        Map<Long, RankingAventureiroDto> ranking = acumularMetricas(participacoes);

        return ranking.values().stream()
                .sorted(Comparator
                        .comparingInt(RankingAventureiroDto::getTotalParticipacoes).reversed()
                        .thenComparingInt(RankingAventureiroDto::getSomaRecompensas).reversed()
                        .thenComparingInt(RankingAventureiroDto::getQuantidadeDestaques).reversed())
                .collect(Collectors.toList());
    }

    private Map<Long, RankingAventureiroDto> acumularMetricas(List<ParticipacaoMissao> participacoes) {
        Map<Long, RankingAventureiroDto> ranking = new HashMap<>();

        for (ParticipacaoMissao p : participacoes) {
            Long aventureiroId = p.getAventureiro().getId();

            ranking.compute(aventureiroId, (id, dto) -> {
                if (dto == null) {
                    dto = new RankingAventureiroDto(
                            id,
                            p.getAventureiro().getNome(),
                            p.getAventureiro().getClasse().name(),
                            p.getAventureiro().getNivel(),
                            0,
                            0,
                            0
                    );
                }
                dto.setTotalParticipacoes(dto.getTotalParticipacoes() + 1);
                dto.setSomaRecompensas(dto.getSomaRecompensas()
                        + (p.getRecompensaOuro() != null ? p.getRecompensaOuro() : 0));
                if (Boolean.TRUE.equals(p.getMvp())) {
                    dto.setQuantidadeDestaques(dto.getQuantidadeDestaques() + 1);
                }
                return dto;
            });
        }

        return ranking;
    }

    public List<RelatorioMissaoDto> gerarRelatorioMissoes(LocalDateTime inicio, LocalDateTime fim) {

        Timestamp tsInicio = Timestamp.valueOf(inicio);
        Timestamp tsFim = Timestamp.valueOf(fim);

        List<Missao> missoes = missaoRepository.findAll()
                .stream()
                .filter(m -> !m.getCriadoem().before(tsInicio) && !m.getCriadoem().after(tsFim))
                .collect(Collectors.toList());

        return construirRelatorioMissoes(missoes);
    }

    private List<RelatorioMissaoDto> construirRelatorioMissoes(List<Missao> missoes) {
        return missoes.stream()
                .map(m -> {
                    List<ParticipacaoMissao> participacoes = participacaoMissaoRepository.findByMissaoId(m.getId());
                    return calcularMetricasMissao(m, participacoes);
                })
                .collect(Collectors.toList());
    }

    private RelatorioMissaoDto calcularMetricasMissao(Missao m, List<ParticipacaoMissao> participacoes) {
        long quantidadeParticipantes = participacoes.stream()
                .map(p -> p.getAventureiro().getId())
                .distinct()
                .count();

        int totalRecompensas = participacoes.stream()
                .filter(p -> p.getRecompensaOuro() != null)
                .mapToInt(ParticipacaoMissao::getRecompensaOuro)
                .sum();

        return new RelatorioMissaoDto(
                m.getId(),
                m.getTitulo(),
                m.getStatus(),
                m.getNivelPerigo(),
                (int) quantidadeParticipantes,
                totalRecompensas
        );
    }



}
