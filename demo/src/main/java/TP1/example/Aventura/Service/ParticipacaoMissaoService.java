package TP1.example.Aventura.Service;

import TP1.example.Aventura.Domain.*;
import TP1.example.Aventura.Dto.ParticipacaoRegistroDto;
import TP1.example.Aventura.Dto.ParticipacaoTudo;
import TP1.example.Aventura.OrganizacaoValidator;
import TP1.example.Aventura.Repository.AventureiroRepository;
import TP1.example.Aventura.Repository.MissaoRepository;
import TP1.example.Aventura.Repository.ParticipacaoMissaoRepository;
import TP1.example.Aventura.Utils.FormatarTimeStamp;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ParticipacaoMissaoService {

    private final ParticipacaoMissaoRepository participacaoRepository;
    private final MissaoRepository missaoRepository;
    private final AventureiroRepository essencialNomeDto;
    private final OrganizacaoValidator organizacaoValidator;

    public Page<ParticipacaoTudo> listarTodos(int page, int size) {
        return participacaoRepository.findAll(PageRequest.of(page, size))
                .map(p -> new ParticipacaoTudo(
                        p.getMissao().getTitulo(),
                        p.getAventureiro().getNome(),
                        p.getPapel().name(),
                        p.getRecompensaOuro(),
                        p.getMvp(),
                        FormatarTimeStamp.TimeStampParaString(p.getCriadoem())
                ));
    }
    public Page<ParticipacaoTudo> listarPorMissao(Long missaoId, int page, int size) {
        return participacaoRepository.findByMissaoId(missaoId, PageRequest.of(page, size))
                .map(p -> new ParticipacaoTudo(
                        p.getMissao().getTitulo(),
                        p.getAventureiro().getNome(),
                        p.getPapel().name(),
                        p.getRecompensaOuro(),
                        p.getMvp(),
                        FormatarTimeStamp.TimeStampParaString(p.getCriadoem())
                ));
    }

    public Page<ParticipacaoTudo> listarPorAventureiro(Long aventureiroId, int page, int size) {
        return participacaoRepository.findByAventureiroId(aventureiroId, PageRequest.of(page, size))
                .map(p -> new ParticipacaoTudo(
                        p.getMissao().getTitulo(),
                        p.getAventureiro().getNome(),
                        p.getPapel().name(),
                        p.getRecompensaOuro(),
                        p.getMvp(),
                        FormatarTimeStamp.TimeStampParaString(p.getCriadoem())
                ));
    }
    @Transactional
    public ParticipacaoMissao salvar(ParticipacaoRegistroDto dto) {
        Aventureiro aventureiro = essencialNomeDto.findById(dto.getAventureiroId())
                .orElseThrow(() -> new EntityNotFoundException("Aventureiro não encontrado"));

        Missao missao = missaoRepository.findById(dto.getMissaoId())
                .orElseThrow(() -> new EntityNotFoundException("Missão não encontrada"));

        if (aventureiro.getStatus() == StatusAventureiro.INATIVO) {
            throw new IllegalStateException("Aventureiro inativo não pode participar de missões");
        }

        if (missao.getStatus() != StatusMissao.PLANEJADA
                && missao.getStatus() != StatusMissao.EM_ANDAMENTO) {
            throw new IllegalStateException("Missão não está em estado compatível para aceitar participantes");
        }

        if (!aventureiro.getOrganizacao().getId()
                .equals(missao.getOrganizacao().getId())) {
            throw new IllegalStateException("Aventureiro não pertence à organização da missão");
        }

        if (participacaoRepository.existsByMissaoIdAndAventureiroId(
                missao.getId(), aventureiro.getId())) {
            throw new IllegalStateException("Aventureiro já está participando desta missão");
        }

        ParticipacaoMissaoId id = new ParticipacaoMissaoId(missao.getId(), aventureiro.getId());

        ParticipacaoMissao participacao = new ParticipacaoMissao();
        participacao.setId(id);
        participacao.setMissao(missao);
        participacao.setAventureiro(aventureiro);
        participacao.setPapel(dto.getPapel());
        participacao.setRecompensaOuro(dto.getRecompensaOuro());

        return participacaoRepository.save(participacao);
    }
    @Transactional
    public void deletar(Long missaoId, Long aventureiroId) {
        ParticipacaoMissaoId id = new ParticipacaoMissaoId(missaoId, aventureiroId);
        if (!participacaoRepository.existsById(id)) {
            throw new EntityNotFoundException("Participação não encontrada");
        }
        participacaoRepository.deleteById(id);
    }
    @Transactional
    public void atualizarPapel(Long missaoId, Long aventureiroId, PapelMissao papel) {
        ParticipacaoMissaoId id = new ParticipacaoMissaoId(missaoId, aventureiroId);
        ParticipacaoMissao participacao = participacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Participação não encontrada"));
        participacao.setPapel(papel);
        participacaoRepository.save(participacao);
    }
    @Transactional
    public void definirMvp(Long missaoId, Long aventureiroId) {
        ParticipacaoMissaoId id = new ParticipacaoMissaoId(missaoId, aventureiroId);
        ParticipacaoMissao participacao = participacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Participação não encontrada"));
        participacao.setMvp(true);
        participacaoRepository.save(participacao);
    }
    @Transactional
    public void definirRecompensa(Long missaoId, Long aventureiroId, Integer ouro) {
        ParticipacaoMissaoId id = new ParticipacaoMissaoId(missaoId, aventureiroId);
        ParticipacaoMissao participacao = participacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Participação não encontrada"));
        participacao.setRecompensaOuro(ouro);
        participacaoRepository.save(participacao);
    }
}