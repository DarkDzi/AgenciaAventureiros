package TP1.example.Aventura.Repository;

import TP1.example.Aventura.Domain.Missao;
import TP1.example.Aventura.Domain.StatusMissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissaoRepository extends JpaRepository<Missao, Long> {
    List<Missao> findByOrganizacaoId(Long organizacaoId);
    List<Missao> findByStatus(StatusMissao status);
    List<Missao> findByOrganizacaoIdAndStatus(Long organizacaoId, StatusMissao status);
}