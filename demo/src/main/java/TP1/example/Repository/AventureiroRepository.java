package TP1.example.Repository;

import TP1.example.Domain.Aventureiro;
import TP1.example.Domain.Classe;
import TP1.example.Domain.StatusAventureiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AventureiroRepository extends JpaRepository<Aventureiro, Long> {
    List<Aventureiro> findByStatus(StatusAventureiro status);
    List<Aventureiro> findByNivelGreaterThan(Integer nivel);
    List<Aventureiro> findByClasse(Classe classe);
}
