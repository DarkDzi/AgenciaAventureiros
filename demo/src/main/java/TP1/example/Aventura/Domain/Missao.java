package TP1.example.Aventura.Domain;

import TP1.example.Audit.Domain.Organizacoes;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "missao", schema = "aventura")
public class Missao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "organizacao_id", nullable = false)
    private Organizacoes organizacao;

    @Column(nullable = false, length = 150)
    @Size(max = 150)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_perigo", nullable = false)
    private NiveldePerigo nivelPerigo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusMissao status = StatusMissao.PLANEJADA;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp criadoem;

    @Column(name = "iniciada_em")
    private Timestamp iniciadaem;

    @Column(name = "terminada_em")
    private Timestamp terminadaem;

    @PrePersist
    protected void prePersist() {
        this.criadoem = new Timestamp(System.currentTimeMillis());
    }
}