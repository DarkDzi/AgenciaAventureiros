package TP1.example.Audit.Repository;

import TP1.example.Audit.Domain.UserRoleId;
import TP1.example.Audit.Domain.Usuario;
import TP1.example.Audit.Domain.Usuario_Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Usuario_CargoRepositry extends JpaRepository<Usuario_Cargo, UserRoleId> {
}
