package TP1.example.operacoes.Repository;

import TP1.example.operacoes.Domain.PainelTaticoMissao;
import TP1.example.operacoes.Dto.PainelTaticoMissaoTudo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PainelTaticoMissaoRepository extends JpaRepository<PainelTaticoMissao, Long> {

    Page<PainelTaticoMissaoTudo> findTop10ByUltimaAtualizacaoAfterOrderByIndiceProntidaoDesc(
            Timestamp ultimaAtualizacao, Pageable pageable
    );
}
