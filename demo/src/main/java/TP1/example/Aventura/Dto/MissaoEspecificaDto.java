package TP1.example.Aventura.Dto;

import TP1.example.Audit.Domain.Organizacoes;
import TP1.example.Aventura.Domain.Aventureiro;
import TP1.example.Aventura.Domain.NiveldePerigo;
import TP1.example.Aventura.Domain.PapelMissao;
import TP1.example.Aventura.Domain.StatusMissao;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@AllArgsConstructor
public class MissaoEspecificaDto {

    private Long id;
    private String organizacao;
    private String titulo;
    private NiveldePerigo nivelPerigo;
    private StatusMissao status;
    private String criadoem;
    private String iniciadaem;
    private String terminadaem;
    private List<ParcipanteMissaoEspecificaDto> parcipantesepapel;
    private Integer recompensa_ouro;
    private List<String> mvps;




}
