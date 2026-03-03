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

    @Column(nullable = false)
    private Companheiro companheiro;


    protected Aventureiro() {
    }
}
