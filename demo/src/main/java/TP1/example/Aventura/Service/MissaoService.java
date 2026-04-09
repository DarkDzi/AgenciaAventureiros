package TP1.example.Aventura.Service;

import TP1.example.Aventura.Domain.Missao;
import TP1.example.Aventura.Domain.NiveldePerigo;
import TP1.example.Aventura.Domain.ParticipacaoMissao;
import TP1.example.Aventura.Domain.StatusMissao;
import TP1.example.Aventura.Dto.MissaoEspecificaDto;
import TP1.example.Aventura.Dto.ParcipanteMissaoEspecificaDto;
import TP1.example.Aventura.Dto.ResultadoMinimoMissaoDto;
import TP1.example.Aventura.OrganizacaoValidator;
import TP1.example.Aventura.Repository.MissaoRepository;
import TP1.example.Aventura.Repository.ParticipacaoMissaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MissaoService {

    private final MissaoRepository missaoRepository;
    private final ParticipacaoMissaoRepository participacaoMissaoRepository;
    private final OrganizacaoValidator organizacaoValidator;

    public Page<Missao> ListarTodos(int page, int size) {
        return missaoRepository.findAll(PageRequest.of(page, size));
    }

    public Page<Missao> ListarPorNivel(NiveldePerigo nivel, int page, int size) {
        return missaoRepository.findBynivelPerigo(nivel, PageRequest.of(page, size));
    }

    public Page<Missao> ListarPorStatus(StatusMissao status,int page, int size) {
        return missaoRepository.findByStatus(status, PageRequest.of(page, size));
    }
    public Page<ResultadoMinimoMissaoDto> ListarPorIntervaloDeCricao(LocalDateTime inicio, LocalDateTime fim, int page, int size){
        return missaoRepository.findByCriadoEmBetween(inicio, fim, PageRequest.of(page, size))
                .map(a -> new ResultadoMinimoMissaoDto(
                        a.getId(),
                        a.getTitulo(),
                        a.getStatus(),
                        a.getNivelPerigo(),
                        a.getIniciadaem(),
                        a.getTerminadaem(),
                        a.getCriadoem()
                ));
    }
    public Page<ResultadoMinimoMissaoDto> ListarPorIntervaloDeComecoeFim(LocalDateTime inicio, LocalDateTime fim, int page, int size) {
        return missaoRepository.findByIniciadoEmGreaterThanEqualAndTerminadoEmLessThanEqual(
                        inicio, fim, PageRequest.of(page, size))
                .map(a -> new ResultadoMinimoMissaoDto(
                        a.getId(),
                        a.getTitulo(),
                        a.getStatus(),
                        a.getNivelPerigo(),
                        a.getIniciadaem(),
                        a.getTerminadaem(),
                        a.getCriadoem()
                ));
    }




    public MissaoEspecificaDto BuscarEspecificoPorId(Long id) {
        Optional<Missao> opta = missaoRepository.findById(id);
        Missao a = opta.orElse(null);
        List<ParticipacaoMissao> pm = participacaoMissaoRepository.findByMissaoId(id);
        List<ParcipanteMissaoEspecificaDto> participantespapel = new ArrayList<>();
        List<String> mvps = new ArrayList<>();
        Integer recompensageral = 0;
        for (ParticipacaoMissao p : pm) {
            participantespapel.add(new ParcipanteMissaoEspecificaDto( p.getAventureiro().getNome(), p.getPapel()));
            if(p.getMvp()){
               mvps.add(p.getAventureiro().getNome());
            }
            recompensageral = recompensageral + p.getRecompensaOuro();
        }

      return new MissaoEspecificaDto(
              a.getId(),
              a.getOrganizacao(),
              a.getTitulo(),
              a.getNivelPerigo(),
              a.getStatus(),
              a.getCriadoem(),
              a.getIniciadaem(),
              a.getTerminadaem(),
              participantespapel,
              recompensageral,
              mvps
      );
    }
    public Missao BuscarPorId(Long id) {
      return missaoRepository.findById(id).orElse(null);
    };

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