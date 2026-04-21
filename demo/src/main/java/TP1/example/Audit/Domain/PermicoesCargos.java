package TP1.example.Audit.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "role_permissions", schema = "audit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermicoesCargos {
    @EmbeddedId
    private RolePermissionID id;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Cargo role;

    @ManyToOne
    @MapsId("permissionId")
    @JoinColumn(name = "permission_id")
    private Permicoes permission;

}
