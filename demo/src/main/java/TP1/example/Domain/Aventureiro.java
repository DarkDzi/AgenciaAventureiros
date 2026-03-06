package TP1.example.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
public class Aventureiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Classe classe;

    @Column(nullable = false)
    @Min(1)
    private Integer nivel;

    @Column(nullable = false)
    private StatusAventureiro status = StatusAventureiro.ATIVO;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "nome", column = @Column(name = "companheiro_nome")),
            @AttributeOverride(name = "especie", column = @Column(name = "companheiro_especie")),
            @AttributeOverride(name = "lealdade", column = @Column(name =  "companheiro_lealdade"))
    })
    private Companheiro companheiro;

    public Aventureiro(Companheiro companheiro, StatusAventureiro status, Integer nivel, Classe classe, String nome, Long id) {
        this.companheiro = companheiro;
        this.status = status;
        this.nivel = nivel;
        this.classe = classe;
        this.nome = nome;
        this.id = id;
    }

    protected Aventureiro() {
    }
}
