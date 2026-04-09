package TP1.example.Audit.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;

@Entity
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "audit_entries", schema="audit")
public class EntradasAuditorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizacao_id")
    private Organizacoes organizacao;

    @JoinColumn(name = "actor_user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario actuserid;

    @JoinColumn(name = "actor_api_key_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ChaveAPI actapikeyid;

    @Column(name = "action", nullable = false)
    private String acao;

    @Column(name = "entity_schema", nullable = false)
    private String entidadeschema;

    @Column(name= "entity_name", nullable = false)
    private String entidadenome;

    @Column(name = "entity_id")
    private String entidadeid;

    @Column(name = "occurred_at", nullable = false)
    private Timestamp ocorridoem;

    @JdbcTypeCode(SqlTypes.OTHER)
    @Column(name = "ip", nullable = false)
    private String ip;

    @Column(name = "user_agent", nullable = false)
    private String usuarioagente;

    @Column(name = "diff",columnDefinition = "jsonb", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private String diff;

    @Column(name = "metadata",columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private String metadata;

    @Column(name = "success", nullable = false)
    private Boolean sucesso;


}
