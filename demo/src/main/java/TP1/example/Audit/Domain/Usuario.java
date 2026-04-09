package TP1.example.Audit.Domain;

import jakarta.persistence.*;
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
@Table(name = "usuarios", schema = "audit")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizacao_id")
    private Organizacoes organizacao;

    @Column(name= "nome", nullable = false)
    private String nome;

    @Column(name= "email", nullable = false, unique = true)
    private String email;

    @Column(name = "senha_hash", nullable = false)
    private String senhahash;


    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusUsuario status = StatusUsuario.ATIVO;

    @Column(name = "ultimo_login_em")
    private Timestamp ultimologin;

    @Column(name= "created_at", nullable = false)
    private Timestamp criadoem;

    @Column(name = "updated_at")
    private Timestamp atualizadoem;



}
