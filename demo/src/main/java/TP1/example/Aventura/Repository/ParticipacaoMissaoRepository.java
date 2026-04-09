package TP1.example.Aventura.Repository;

import TP1.example.Aventura.Domain.ParticipacaoMissao;
import TP1.example.Aventura.Domain.ParticipacaoMissaoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipacaoMissaoRepository
        extends JpaRepository<ParticipacaoMissao, ParticipacaoMissaoId> {
    List<ParticipacaoMissao> findByMissaoId(Long missaoId);
    List<ParticipacaoMissao> findByAventureiroId(Long aventureiroId);
    boolean existsByMissaoIdAndAventureiroId(Long missaoId, Long aventureiroId);
}