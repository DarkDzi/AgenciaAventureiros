package TP1.example.Aventura.Dto;

import TP1.example.Aventura.Domain.CompanheiroEspecie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class CompanheiroRegistroDto {
    private String nome;
    private CompanheiroEspecie especie;
    private Integer lealdade;
}
