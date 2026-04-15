package TP1.example.Aventura.Repository;

import TP1.example.Aventura.Domain.Missao;
import TP1.example.Aventura.Domain.NiveldePerigo;
import TP1.example.Aventura.Domain.StatusMissao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public interface MissaoRepository extends JpaRepository<Missao, Long> {
    Page<Missao> findByStatus(StatusMissao status, Pageable pageable);
    Page<Missao> findBynivelPerigo(NiveldePerigo nivel, Pageable pageable);
    Page<Missao> findByCriadoemBetween(Timestamp inicio, Timestamp fim, Pageable pageable);
    @Query("SELECT m FROM Missao m WHERE m.iniciadaem IS NOT NULL AND m.terminadaem IS NOT NULL AND m.iniciadaem >= :inicio AND m.terminadaem <= :fim")
    Page<Missao> findByPeriodo(
            @Param("inicio") Timestamp inicio,
            @Param("fim") Timestamp fim,
            Pageable pageable
    );


}