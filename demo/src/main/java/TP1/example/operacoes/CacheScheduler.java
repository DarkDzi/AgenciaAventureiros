package TP1.example.operacoes;

import TP1.example.operacoes.Services.PainelTaticoMissaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CacheScheduler {
    private final PainelTaticoMissaoService Service;
    //Toda vez que fazer a busca guarda o resultado em cache e com @Schedule a cada 1minuto exucuta o atualizarcache que chama o limpar e deposi buscar
    @Scheduled(fixedRate =  5 * 60000) //60000milisegundos = 1minuto
    public void atualizarCacher(){
        Service.limparcache();
        Service.buscarTop10Ultimos15Dias();
    }
}
