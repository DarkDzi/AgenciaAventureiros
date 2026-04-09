package TP1.example.Aventura.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "participacao_missao", schema = "aventura")
public class ParticipacaoMissao {

    @EmbeddedId
    private ParticipacaoMissaoId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("missaoId")
    @JoinColumn(name = "missao_id", nullable = false)
    private Missao missao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("aventureiroId")
    @JoinColumn(name = "aventureiro_id", nullable = false)
    private Aventureiro aventureiro;

    @Enumerated(EnumType.STRING)
    @Column(name = "papel", nullable = false)
    private PapelMissao papel;

    @Column(name = "recompensa_ouro")
    @Min(0)
    private Integer recompensaOuro;

    @Column(nullable = false)
    private Boolean mvp = false;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp criadoem;

    @PrePersist
    protected void prePersist() {
        this.criadoem = new Timestamp(System.currentTimeMillis());
    }
}