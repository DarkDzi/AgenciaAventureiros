package TP1.example.Audit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter@Setter
@Table(name="organizacoes", schema="audit")
public class Organizacoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @Column(name = "created_at", nullable = false)
    private Timestamp criadoem;

    public Organizacoes(Long id, String nome, Boolean ativo, Timestamp criadoem) {
        this.id = id;
        this.nome = nome;
        this.ativo = ativo;
        this.criadoem = criadoem;
    }

    protected Organizacoes(){};
}
