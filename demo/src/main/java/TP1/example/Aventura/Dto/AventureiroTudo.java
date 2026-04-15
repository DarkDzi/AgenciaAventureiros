package TP1.example.Aventura.Dto;

import TP1.example.Aventura.Domain.Classe;
import TP1.example.Aventura.Domain.Companheiro;
import TP1.example.Aventura.Domain.StatusAventureiro;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AventureiroTudo {
    private Long id;
    private Long organizacaoId;
    private String usuarioResponsavelNome;
    private String nome;
    private Classe classe;
    private Integer nivel;
    private StatusAventureiro status;
    private String companheiroEspecie;
    private String criadoEm;
    private String atualizadoEm;


}
