package TP1.example.Audit;

import TP1.example.Audit.Domain.PermicoesCargos;
import TP1.example.Audit.Repository.Permicoes_CargoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class Permicoes_CargoTest {
    @Autowired
    private Permicoes_CargoRepository permissionrolerepository;

    @Test
    void acessarpermicoesporcargo() {
        List<PermicoesCargos> permicoescargo = permissionrolerepository.findByRoleId(1L);

        assertThat(permicoescargo)
                .allMatch(u -> u.getPermission() != null);
    }

}
