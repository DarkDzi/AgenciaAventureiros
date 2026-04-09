package TP1.example.Aventura.Repository;

import TP1.example.Aventura.Domain.Missao;
import TP1.example.Aventura.Domain.NiveldePerigo;
import TP1.example.Aventura.Domain.StatusMissao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MissaoRepository extends JpaRepository<Missao, Long> {
    Page<Missao> findByStatus(StatusMissao status, Pageable pageable);
    Page<Missao> findBynivelPerigo(NiveldePerigo nivel, Pageable pageable);
    Page<Missao> findByCriadoEmBetween(LocalDateTime inicio, LocalDateTime fim, Pageable pageable);
    Page<Missao> findByIniciadoEmGreaterThanEqualAndTerminadoEmLessThanEqual(
            LocalDateTime inicio, LocalDateTime fim, Pageable pageable
    );


}