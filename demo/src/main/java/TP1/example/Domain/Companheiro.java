package TP1.example.Domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.springframework.data.annotation.Id;

@Embeddable
@Getter@Setter
public class Companheiro {
    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompanheiroEspecie especie;

    @Column(nullable = false)
    @Min(0)
    @Max(100)
    private Integer lealdade;



}
