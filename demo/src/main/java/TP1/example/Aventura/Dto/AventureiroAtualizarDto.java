package TP1.example.Aventura.Dto;

import TP1.example.Aventura.Domain.Classe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AventureiroAtualizarDto {
    private String nome;
    private Classe classe;
    private Integer nivel;
}
