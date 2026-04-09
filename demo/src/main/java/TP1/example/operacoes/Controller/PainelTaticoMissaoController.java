package TP1.example.operacoes.Controller;



import TP1.example.operacoes.Domain.PainelTaticoMissao;
import TP1.example.operacoes.Services.PainelTaticoMissaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/missoes")
@RequiredArgsConstructor
public class PainelTaticoMissaoController {

    private final PainelTaticoMissaoService service;

    @GetMapping("/top15dias")
    public List<PainelTaticoMissao> buscarTop10Ultimos15Dias() {
        return service.buscarTop10Ultimos15Dias();
    }
}
