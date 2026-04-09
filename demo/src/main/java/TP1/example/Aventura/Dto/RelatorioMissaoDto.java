package TP1.example.Aventura.Dto;

import TP1.example.Aventura.Domain.NiveldePerigo;
import TP1.example.Aventura.Domain.StatusMissao;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RelatorioMissaoDto {
    private Long missaoId;
    private String titulo;
    private StatusMissao status;
    private NiveldePerigo nivelPerigo;
    private int quantidadeParticipantes;
    private int totalRecompensas;
}
