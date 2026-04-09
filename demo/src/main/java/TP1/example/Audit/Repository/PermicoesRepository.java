package TP1.example.Audit.Repository;

import TP1.example.Audit.Domain.Permicoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermicoesRepository extends JpaRepository<Permicoes, Long> {
}
