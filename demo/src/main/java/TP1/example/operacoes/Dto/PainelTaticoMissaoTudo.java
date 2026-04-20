package TP1.example.operacoes.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class PainelTaticoMissaoTudo {
    private Long missaoId;
    private String titulo;
    private String status;
    private String nivelPerigo;
    private Long organizacaoId;
    private Integer totalParticipantes;
    private BigDecimal nivelMedioEquipe;
    private Integer totalRecompensa;
    private Integer totalMvps;
    private Integer participantesComCompanheiro;
    private String ultimaAtualizacao;
    private BigDecimal indiceProntidao;
}
