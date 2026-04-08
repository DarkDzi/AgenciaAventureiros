package TP1.example.Audit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "role_permissions")
@Getter
@Setter
public class Permicoes_Cargos {
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

    protected Permicoes_Cargos() {}

    public Permicoes_Cargos(Permicoes permission, Cargo role, RolePermissionID id) {
        this.permission = permission;
        this.role = role;
        this.id = id;
    }
}
