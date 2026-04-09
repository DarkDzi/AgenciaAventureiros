package TP1.example.Aventura.Domain;

import TP1.example.Audit.Domain.Organizacoes;
import TP1.example.Audit.Domain.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "aventureiro", schema = "aventura")
public class Aventureiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "organizacao_id", nullable = false)
    private Organizacoes organizacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuarioResponsavel;

    @Column(nullable = false, length = 120)
    @Size(max = 120)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Classe classe;

    @Column(nullable = false)
    @Min(1)
    private Integer nivel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAventureiro status = StatusAventureiro.ATIVO;

    @OneToOne(cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY

    )
    @JoinColumn(name = "companheiro_id")
    private Companheiro companheiro;
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp criadoem;

    @Column(name = "updated_at", nullable = false)
    private Timestamp atualizadoem;

    @PrePersist
    protected void prePersist() {
        this.criadoem = new Timestamp(System.currentTimeMillis());
        this.atualizadoem = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void preUpdate() {
        this.atualizadoem = new Timestamp(System.currentTimeMillis());
    }


}
