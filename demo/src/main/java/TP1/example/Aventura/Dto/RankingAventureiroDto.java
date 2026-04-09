package TP1.example.Aventura.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RankingAventureiroDto {
    private Long aventureiroId;
    private String nomeAventureiro;
    private String classe;
    private Integer nivel;
    private int totalParticipacoes;
    private int somaRecompensas;
    private int quantidadeDestaques;
}
