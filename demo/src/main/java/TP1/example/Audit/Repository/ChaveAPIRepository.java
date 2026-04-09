package TP1.example.Audit.Repository;

import TP1.example.Audit.Domain.ChaveAPI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChaveAPIRepository extends JpaRepository<ChaveAPI, Long> {
}
