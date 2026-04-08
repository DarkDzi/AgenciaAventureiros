package TP1.example.Audit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter@Setter
@Table(name= "roles", schema="audit")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name= "organizacao_id",nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Organizacoes orgid;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "created_at", nullable = false)
    private Timestamp criadoem;

    public Cargo(Long id, Organizacoes orgid, String nome, String descricao, Timestamp criadoem) {
        this.id = id;
        this.orgid = orgid;
        this.nome = nome;
        this.descricao = descricao;
        this.criadoem = criadoem;
    }
    protected Cargo() {};
}
