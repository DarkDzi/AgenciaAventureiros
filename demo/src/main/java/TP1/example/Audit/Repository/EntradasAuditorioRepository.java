package TP1.example.Audit.Repository;

import TP1.example.Audit.Domain.EntradasAuditorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradasAuditorioRepository extends JpaRepository<EntradasAuditorio, Long> {
}
