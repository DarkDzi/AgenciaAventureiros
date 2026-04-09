package TP1.example.Aventura.Dto;

import TP1.example.Aventura.Domain.Classe;
import TP1.example.Aventura.Domain.Companheiro;
import TP1.example.Aventura.Domain.Missao;
import TP1.example.Aventura.Domain.StatusAventureiro;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PerfilCompletoDto {
    private Long id;
    private String nome;
    private Classe classe;
    private Integer nivel;
    private StatusAventureiro status;
    private Companheiro companheiro;
    private Integer totalMissoes;
    private Missao ultimamissao;
}
