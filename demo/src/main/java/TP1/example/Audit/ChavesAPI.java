package TP1.example.Audit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.sql.Timestamp;

@Entity
@Getter@Setter
@Table(name="api_keys", schema="audit")
public class ChavesAPI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "organizacao_id", nullable = false)
    private Long orgid;

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

    public ChavesAPI(Long id, Long orgid, String nome, String chavehash, Boolean ativo, Timestamp criadoem, Timestamp ultimouso) {
        this.id = id;
        this.orgid = orgid;
        this.nome = nome;
        this.chavehash = chavehash;
        this.ativo = ativo;
        this.criadoem = criadoem;
        this.ultimouso = ultimouso;
    }

    protected ChavesAPI(){}
}
