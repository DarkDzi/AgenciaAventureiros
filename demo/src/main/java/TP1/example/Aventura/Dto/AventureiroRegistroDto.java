package TP1.example.Aventura.Dto;

import TP1.example.Aventura.Domain.Classe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class AventureiroRegistroDto {
    private Long organizacaoId;
    private Long usuarioResponsavelId;
    private String nome;
    private Classe classe;
    private Integer nivel;
}
