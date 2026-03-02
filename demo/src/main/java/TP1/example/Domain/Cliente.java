package TP1.example.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
public class Cliente{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false, unique = true)
    private String email;

    public Cliente(Long id, String email, String cpf, String nome) {
        this.id = id;
        this.email = email;
        this.cpf = cpf;
        this.nome = nome;
    }

    protected Cliente() {
    }
}
