package TP1.example.Audit.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
public class RolePermissionID implements Serializable {

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "permission_id")
    private Long permissionId;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof RolePermissionID)) return false;
        RolePermissionID that = (RolePermissionID) o;
        return Objects.equals(roleId, that.roleId) &&
                Objects.equals(permissionId, that.permissionId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(roleId, permissionId);
    }




    protected RolePermissionID() {}

    public RolePermissionID(Long roleId, Long permissionId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
    }
}
