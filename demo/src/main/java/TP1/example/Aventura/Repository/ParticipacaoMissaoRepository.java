package TP1.example.Aventura.Repository;

import TP1.example.Aventura.Domain.ParticipacaoMissao;
import TP1.example.Aventura.Domain.ParticipacaoMissaoId;
import TP1.example.Aventura.Domain.StatusMissao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ParticipacaoMissaoRepository extends JpaRepository<ParticipacaoMissao, ParticipacaoMissaoId> {
    Page<ParticipacaoMissao> findByMissaoId(Long missaoId, Pageable pageable);

    List<ParticipacaoMissao> findByMissaoId(Long missaoid);

    Page<ParticipacaoMissao> findByAventureiroId(Long aventureiroId, Pageable pageable);

    List<ParticipacaoMissao> findByAventureiroId(Long aventureiroId);

    int countByAventureiroId(Long aventureiroId);

    boolean existsByMissaoIdAndAventureiroId(Long missaoId, Long aventureiroId);

    @Query("SELECT p FROM ParticipacaoMissao p WHERE p.criadoem BETWEEN :inicio AND :fim AND (:status IS NULL OR p.missao.status = :status)")
    List<ParticipacaoMissao> buscarParaRanking(
            @Param("inicio") Timestamp inicio,
            @Param("fim") Timestamp fim,
            @Param("status")StatusMissao status
            );

}