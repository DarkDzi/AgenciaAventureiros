package TP1.example.Aventura.Dto;

import TP1.example.Aventura.Domain.NiveldePerigo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class MissaoRegistroDto {
    private Long organizacaoId;
    private String titulo;
    private NiveldePerigo nivelPerigo;
}
