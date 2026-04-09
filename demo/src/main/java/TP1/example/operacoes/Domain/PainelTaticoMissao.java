package TP1.example.operacoes.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@Immutable
@Table(name = "vw_painel_tatico_missao", schema = "operacoes")
public class PainelTaticoMissao {

    @Id
    @Column(name = "missao_id")
    private Long missaoId;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "status")
    private String status;

    @Column(name = "nivel_perigo")
    private String nivelPerigo;

    @Column(name = "organizacao_id")
    private Long organizacaoId;

    @Column(name = "total_participantes")
    private Integer totalParticipantes;

    @Column(name = "nivel_medio_equipe")
    private BigDecimal nivelMedioEquipe;

    @Column(name = "total_recompensa")
    private Integer totalRecompensa;

    @Column(name = "total_mvps")
    private Integer totalMvps;

    @Column(name = "participantes_com_companheiro")
    private Integer participantesComCompanheiro;

    @Column(name = "ultima_atualizacao")
    private Timestamp ultimaAtualizacao;

    @Column(name = "indice_prontidao")
    private BigDecimal indiceProntidao;
}