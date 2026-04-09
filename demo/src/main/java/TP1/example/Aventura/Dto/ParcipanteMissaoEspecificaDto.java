package TP1.example.Aventura.Dto;

import TP1.example.Aventura.Domain.PapelMissao;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ParcipanteMissaoEspecificaDto {
    private String nome;
    private PapelMissao papelmissao;

}
