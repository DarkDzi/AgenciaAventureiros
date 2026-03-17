package TP1.example.Audit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "usuarios", schema = "audit")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "organizacao_id", nullable = false)
    private Long orgid;

    @Column(name= "nome", nullable = false)
    private String nome;

    @Column(name= "email", nullable = false, unique = true)
    private String email;

    @Column(name = "senha_hash", nullable = false)
    private String hash;

    @Column(name = "status", nullable = false)
    private StatusUsuario status = StatusUsuario.ATIVO;

    @Column(name = "ultimo_login_em")
    private Timestamp ultimologin;

    @Column(name= "created_at", nullable = false)
    private Timestamp criadoem;

    @Column(name = "updated_at")
    private Timestamp atualizadoem;

    public Usuario(Long id,
                   Long orgid,
                   String nome,
                   String email,
                   String hash,
                   StatusUsuario status,
                   Timestamp ultimologin,
                   Timestamp criadoem,
                   Timestamp atualizadoem)
    {
        this.id = id;
        this.orgid = orgid;
        this.nome = nome;
        this.email = email;
        this.hash = hash;
        this.status = status;
        this.ultimologin = ultimologin;
        this.criadoem = criadoem;
        this.atualizadoem = atualizadoem;
    }
    protected Usuario(){};
}
