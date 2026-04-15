package TP1.example.Aventura.Dto;

import TP1.example.Aventura.Domain.NiveldePerigo;
import TP1.example.Aventura.Domain.StatusAventureiro;
import TP1.example.Aventura.Domain.StatusMissao;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class ResultadoMinimoMissaoDto {
    private Long id;
    private String Titulo;
    private String status;
    private String nivel;
    private String iniciadoem;
    private String terminadoem;
    private String começadoem;
}
