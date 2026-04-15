package TP1.example.Aventura.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MissaoTudo {
    private Long id;
    private String organização;
    private String titulo;
    private String nivelPerigo;
    private String status;
    private String iniciadaEm;
    private String terminadaEm;
    private String criadoEm;

}
