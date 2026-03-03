package TP1.example.Repository;

import TP1.example.Domain.Aventureiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AventureiroRepository extends JpaRepository<Aventureiro, Long> {

    Aventureiro id(Long id);
}
