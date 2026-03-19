package TP1.example.Audit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter@Setter
@Table(name= "roles", schema="audit")
public class Cargos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "organizacao_id",nullable = false)
    private Long orgid;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "created_at", nullable = false)
    private Timestamp criadoem;

    public Cargos(Long id, Long orgid, String nome, String descricao, Timestamp criadoem) {
        this.id = id;
        this.orgid = orgid;
        this.nome = nome;
        this.descricao = descricao;
        this.criadoem = criadoem;
    }
    protected Cargos() {};
}
