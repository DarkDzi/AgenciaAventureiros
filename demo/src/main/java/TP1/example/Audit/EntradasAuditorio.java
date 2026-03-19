package TP1.example.Audit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;

@Entity
@Getter@Setter
@Table(name = "audit_entries", schema="audit")
public class EntradasAuditorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "organizacao_id", nullable = false)
    private Long orgid;

    @Column(name = "actor_user_id")
    private Long actuserid;

    @Column(name = "actor_api_key_id")
    private Long actapikeyid;

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

    public EntradasAuditorio(Long id, Long orgid, Long actuserid, Long actapikeyid, String acao, String entidadeschema, String entidadenome, String entidadeid, Timestamp ocorridoem, String ip, String usuarioagente, String diff, String metadata, Boolean sucesso) {
        this.id = id;
        this.orgid = orgid;
        this.actuserid = actuserid;
        this.actapikeyid = actapikeyid;
        this.acao = acao;
        this.entidadeschema = entidadeschema;
        this.entidadenome = entidadenome;
        this.entidadeid = entidadeid;
        this.ocorridoem = ocorridoem;
        this.ip = ip;
        this.usuarioagente = usuarioagente;
        this.diff = diff;
        this.metadata = metadata;
        this.sucesso = sucesso;
    }

    protected EntradasAuditorio() {};
}
