package TP1.example.Audit.Repository;

import TP1.example.Audit.Domain.PermicoesCargos;
import TP1.example.Audit.Domain.RolePermissionID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Permicoes_CargoRepository extends JpaRepository<PermicoesCargos, RolePermissionID> {
    List<PermicoesCargos> findByRoleId(Long roleId);
}
