package TP1.example.Audit;

import TP1.example.Audit.Domain.Usuario;
import TP1.example.Audit.Repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RelacionamentoOrganicaoTest {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void deveCarregarOrganizacaodoUser(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        assertThat(usuarios)
                .allMatch(u -> u.getOrganizacao().getId() != null);

    }
}
