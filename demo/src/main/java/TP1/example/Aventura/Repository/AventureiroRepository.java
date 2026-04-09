package TP1.example.Aventura.Repository;

import TP1.example.Aventura.Domain.Aventureiro;
import TP1.example.Aventura.Domain.Classe;
import TP1.example.Aventura.Domain.StatusAventureiro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AventureiroRepository extends JpaRepository<Aventureiro, Long> {
    Page<Aventureiro> findByStatus(StatusAventureiro status, Pageable pageable);
    Page<Aventureiro> findByNivelGreaterThan(Integer nivel, Pageable pageable);
    Page<Aventureiro> findByClasse(Classe classe, Pageable pageable);
    Page<Aventureiro> findByNomeContaining(String nome, Pageable pageable);
}
