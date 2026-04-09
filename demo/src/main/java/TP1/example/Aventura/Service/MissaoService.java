package TP1.example.Aventura.Service;

import TP1.example.Aventura.Domain.Missao;
import TP1.example.Aventura.Domain.NiveldePerigo;
import TP1.example.Aventura.Domain.StatusMissao;
import TP1.example.Aventura.OrganizacaoValidator;
import TP1.example.Aventura.Repository.MissaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissaoService {

    private final MissaoRepository missaoRepository;
    private final OrganizacaoValidator organizacaoValidator;

    public List<Missao> ListarTodos() {
        return missaoRepository.findAll();
    }

    public List<Missao> ListarPorOrganizacao(Long organizacaoId) {
        organizacaoValidator.existe(organizacaoId);
        return missaoRepository.findByOrganizacaoId(organizacaoId);
    }

    public List<Missao> ListarPorStatus(StatusMissao status) {
        return missaoRepository.findByStatus(status);
    }

    public List<Missao> ListarPorOrganizacaoEStatus(Long organizacaoId, StatusMissao status) {
        organizacaoValidator.existe(organizacaoId);
        return missaoRepository.findByOrganizacaoIdAndStatus(organizacaoId, status);
    }

    public Missao BuscarPorId(Long id) {
        return missaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Missão não encontrada"));
    }

    public Missao Salvar(Missao missao) {
        organizacaoValidator.existe(missao.getOrganizacao().getId());
        return missaoRepository.save(missao);
    }

    public void Deletar(Long id) {
        BuscarPorId(id);
        missaoRepository.deleteById(id);
    }

    public void AtualizarTitulo(Long id, String titulo) {
        Missao missao = BuscarPorId(id);
        missao.setTitulo(titulo);
        missaoRepository.save(missao);
    }

    public void AtualizarStatus(Long id, StatusMissao status) {
        Missao missao = BuscarPorId(id);
        missao.setStatus(status);
        missaoRepository.save(missao);
    }

    public void AtualizarNivelPerigo(Long id, NiveldePerigo nivelPerigo) {
        Missao missao = BuscarPorId(id);
        missao.setNivelPerigo(nivelPerigo);
        missaoRepository.save(missao);
    }
}