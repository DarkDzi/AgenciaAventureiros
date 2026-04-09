package TP1.example.Aventura.Domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "companheiro", schema = "aventura")
public class Companheiro {

    @Id
    @Column(name = "aventureiro_id")
    private Long aventureiroId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "aventureiro_id", nullable = false)
    private Aventureiro aventureiro;

    @Column(nullable = false, length = 120)
    @Size(max = 120)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompanheiroEspecie especie;

    @Column(nullable = false)
    @Min(0)
    @Max(100)
    private Integer lealdade;



}
