package TP1.example.Audit;

import TP1.example.Audit.Domain.Cargo;
import TP1.example.Audit.Domain.Usuario;
import TP1.example.Audit.Repository.CargoRepository;
import TP1.example.Audit.Repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

@org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRoleRepositoryTest {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CargoRepository cargoRepository;

    @Test
    void deveCarregarRolesDoBanco(){
        List<Cargo> lista = cargoRepository.findAll();
        assertThat(lista).isNotEmpty();
    }
    @Test
    void deveCarregarUsersDoBanco(){
        List<Usuario> lista = usuarioRepository.findAll();
        assertThat(lista).isNotEmpty();
    }

}
