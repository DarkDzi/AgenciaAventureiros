package TP1.example.Audit.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_roles", schema = "audit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}
