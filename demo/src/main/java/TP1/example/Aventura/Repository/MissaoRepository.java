package TP1.example.Aventura.Repository;

import TP1.example.Aventura.Domain.Missao;
import TP1.example.Aventura.Domain.NiveldePerigo;
import TP1.example.Aventura.Domain.StatusMissao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MissaoRepository extends JpaRepository<Missao, Long> {
    Page<Missao> findByStatus(StatusMissao status, Pageable pageable);
    Page<Missao> findBynivelPerigo(NiveldePerigo nivel, Pageable pageable);
    Page<Missao> findByCriadoemBetween(LocalDateTime inicio, LocalDateTime fim, Pageable pageable);
    Page<Missao> findByIniciadaemGreaterThanEqualAndTerminadaemLessThanEqual(
            LocalDateTime inicio, LocalDateTime fim, Pageable pageable
    );


}