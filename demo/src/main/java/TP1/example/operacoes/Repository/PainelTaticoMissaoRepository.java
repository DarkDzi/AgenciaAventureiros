package TP1.example.operacoes.Repository;

import TP1.example.operacoes.Domain.PainelTaticoMissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PainelTaticoMissaoRepository extends JpaRepository<PainelTaticoMissao, Long> {

    List<PainelTaticoMissao> findTop10ByUltimaAtualizacaoAfterOrderByIndiceProntidaoDesc(
            Timestamp ultimaAtualizacao
    );
}
