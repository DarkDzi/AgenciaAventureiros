package TP1.example.Domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.springframework.data.annotation.Id;

@Entity
@Getter@Setter
public class Companheiro {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private String especie;

    @Column(nullable = false)
    @Min(0)
    @Max(100)
    private Integer lealdade;



}
