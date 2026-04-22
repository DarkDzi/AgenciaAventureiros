package TP1.example.Aventura.Service;

import TP1.example.Audit.Domain.Organizacoes;
import TP1.example.Aventura.Domain.Missao;
import TP1.example.Aventura.Domain.NiveldePerigo;
import TP1.example.Aventura.Domain.ParticipacaoMissao;
import TP1.example.Aventura.Domain.StatusMissao;
import TP1.example.Aventura.Dto.*;
import TP1.example.Aventura.OrganizacaoValidator;
import TP1.example.Aventura.Repository.MissaoRepository;
import TP1.example.Aventura.Repository.ParticipacaoMissaoRepository;
import TP1.example.Aventura.Utils.FormatarTimeStamp;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissaoService {

    private final MissaoRepository missaoRepository;
    private final ParticipacaoMissaoRepository participacaoMissaoRepository;
    private final OrganizacaoValidator organizacaoValidator;

    public Page<MissaoTudo> listarTodos(int page, int size) {
        return missaoRepository.findAll(PageRequest.of(page, size))
                .map(m -> new MissaoTudo(
                        m.getId(),
                        m.getOrganizacao().getNome(),
                        m.getTitulo(),
                        m.getNivelPerigo().name(),
                        m.getStatus().name(),
                        FormatarTimeStamp.TimeStampParaString(m.getIniciadaem()),
                        FormatarTimeStamp.TimeStampParaString(m.getTerminadaem()),
                        FormatarTimeStamp.TimeStampParaString(m.getCriadoem())
                ));
    }

    public Page<MissaoTudo> listarPorNivel(NiveldePerigo nivel, int page, int size) {
        return missaoRepository.findBynivelPerigo(nivel, PageRequest.of(page, size))
                .map(m -> new MissaoTudo(
                m.getId(),
                m.getOrganizacao().getNome(),
                m.getTitulo(),
                m.getNivelPerigo().name(),
                m.getStatus().name(),
                FormatarTimeStamp.TimeStampParaString(m.getIniciadaem()),
                FormatarTimeStamp.TimeStampParaString(m.getTerminadaem()),
                FormatarTimeStamp.TimeStampParaString(m.getCriadoem())
        ));
    }

    public Page<MissaoTudo> listarPorStatus(StatusMissao status, int page, int size) {
        return missaoRepository.findByStatus(status, PageRequest.of(page, size)).map(m -> new MissaoTudo(
                m.getId(),
                m.getOrganizacao().getNome(),
                m.getTitulo(),
                m.getNivelPerigo().name(),
                m.getStatus().name(),
                FormatarTimeStamp.TimeStampParaString(m.getIniciadaem()),
                FormatarTimeStamp.TimeStampParaString(m.getTerminadaem()),
                FormatarTimeStamp.TimeStampParaString(m.getCriadoem())
        ));
    }
    public Page<ResultadoMinimoMissaoDto> listarPorIntervaloDeCricao(Timestamp inicio, Timestamp fim, int page, int size){
        return missaoRepository.findByCriadoemBetween(inicio, fim, PageRequest.of(page, size))
                .map(a -> new ResultadoMinimoMissaoDto(
                        a.getId(),
                        a.getTitulo(),
                        a.getStatus().name(),
                        a.getNivelPerigo().name(),
                        FormatarTimeStamp.TimeStampParaString(a.getIniciadaem()),
                        FormatarTimeStamp.TimeStampParaString(a.getTerminadaem()),
                        FormatarTimeStamp.TimeStampParaString(a.getCriadoem())
                ));
    }
    public Page<ResultadoMinimoMissaoDto> listarPorIntervaloDeComecoeFim(Timestamp inicio, Timestamp fim, int page, int size) {
        return missaoRepository.findByPeriodo(
                        inicio, fim, PageRequest.of(page, size))
                .map(a -> new ResultadoMinimoMissaoDto(
                        a.getId(),
                        a.getTitulo(),
                        a.getStatus().name(),
                        a.getNivelPerigo().name(),
                        FormatarTimeStamp.TimeStampParaString(a.getIniciadaem()),
                        FormatarTimeStamp.TimeStampParaString(a.getTerminadaem()),
                        FormatarTimeStamp.TimeStampParaString(a.getCriadoem())
                ));
    }



    @Transactional
    public MissaoEspecificaDto buscarEspecificoPorId(Long id) {
        Missao a = missaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Missão não encontrada"));
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
              a.getOrganizacao().getNome(),
              a.getTitulo(),
              a.getNivelPerigo(),
              a.getStatus(),
              FormatarTimeStamp.TimeStampParaString(a.getCriadoem()),
              FormatarTimeStamp.TimeStampParaString(a.getIniciadaem()),
              FormatarTimeStamp.TimeStampParaString(a.getTerminadaem()),
              participantespapel,
              recompensageral,
              mvps
      );
    }
    @Transactional
    public Missao salvar(MissaoRegistroDto dto) {
        if (!organizacaoValidator.existe(dto.getOrganizacaoId())) {
            throw new EntityNotFoundException("Organização não encontrada");
        }

        Organizacoes organizacao = new Organizacoes();
        organizacao.setId(dto.getOrganizacaoId());

        Missao missao = new Missao();
        missao.setOrganizacao(organizacao);
        missao.setTitulo(dto.getTitulo());
        missao.setNivelPerigo(dto.getNivelPerigo());

        return missaoRepository.save(missao);
    }
    @Transactional
    public void deletar(Long id) {
        missaoRepository.deleteById(id);
    }
    @Transactional
    public void atualizarTitulo(Long id, String titulo) {
        Missao missao = missaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Missão não encontrada"));
        missao.setTitulo(titulo);
        missaoRepository.save(missao);
    }
    @Transactional
    public void atualizarStatus(Long id, StatusMissao status) {
        Missao missao = missaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Missão não encontrada"));
        missao.setStatus(status);
        missaoRepository.save(missao);
    }
    @Transactional
    public void atualizarNivelPerigo(Long id, NiveldePerigo nivelPerigo) {
        Missao missao = missaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Missão não encontrada"));
        missao.setNivelPerigo(nivelPerigo);
        missaoRepository.save(missao);
    }
}