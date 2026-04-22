package TP1.example.Aventura.Dto;

import TP1.example.Aventura.Domain.PapelMissao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ParticipacaoRegistroDto {
    private Long missaoId;
    private Long aventureiroId;
    private PapelMissao papel;
    private Integer recompensaOuro;
}
