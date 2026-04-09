package TP1.example.operacoes.Services;

import TP1.example.operacoes.Domain.PainelTaticoMissao;
import TP1.example.operacoes.Repository.PainelTaticoMissaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PainelTaticoMissaoService {

    private final PainelTaticoMissaoRepository repository;
    //Toda vez que fazer a busca guarda o resultado em cache

    @Cacheable("top15dias")
    public List<PainelTaticoMissao> buscarTop10Ultimos15Dias() {
        Timestamp quinzeDiasAtras = Timestamp.valueOf(
                LocalDateTime.now().minusDays(15)
        );
        return repository
                .findTop10ByUltimaAtualizacaoAfterOrderByIndiceProntidaoDesc(quinzeDiasAtras);
    }
    @CacheEvict(value="top15dias", allEntries = true)
    public void limparcache(){

    }


}
