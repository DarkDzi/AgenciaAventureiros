package TP1.example.Audit.Repository;

import TP1.example.Audit.Domain.Organizacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizacoesRepository extends JpaRepository<Organizacoes, Long> {
}
