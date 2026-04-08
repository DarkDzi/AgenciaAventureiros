package TP1.example.Audit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
public class Usuario_Cargo {
    @EmbeddedId
    private UserRoleId id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Cargo role;

    @Column(name = "granted_at")
    private LocalDateTime grantedAt;

    protected Usuario_Cargo() {}

    public Usuario_Cargo(LocalDateTime grantedAt, Cargo role, Usuario usuario, UserRoleId id) {
        this.grantedAt = grantedAt;
        this.role = role;
        this.usuario = usuario;
        this.id = id;
    }
}
