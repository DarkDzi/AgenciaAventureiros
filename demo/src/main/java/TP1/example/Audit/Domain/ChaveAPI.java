package TP1.example.Audit.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="api_keys", schema="audit")
public class ChaveAPI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizacao_id")
    private Organizacoes organizacao;

    @Column(name = "nome" , nullable = false)
    private String nome;

    @Column(name = "key_hash", nullable = false)
    private String chavehash;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @Column(name = "created_at", nullable = false)
    private Timestamp criadoem;

    @Column(name = "last_used_at")
    private Timestamp ultimouso;


}
