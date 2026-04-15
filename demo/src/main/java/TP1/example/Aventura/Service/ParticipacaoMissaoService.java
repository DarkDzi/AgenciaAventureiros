package TP1.example.Aventura.Service;

import TP1.example.Aventura.Domain.*;
import TP1.example.Aventura.Dto.ParticipacaoTudo;
import TP1.example.Aventura.OrganizacaoValidator;
import TP1.example.Aventura.Repository.AventureiroRepository;
import TP1.example.Aventura.Repository.MissaoRepository;
import TP1.example.Aventura.Repository.ParticipacaoMissaoRepository;
import TP1.example.Aventura.Utils.FormatarTimeStamp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipacaoMissaoService {

    private final ParticipacaoMissaoRepository participacaoRepository;
    private final MissaoRepository missaoRepository;
    private final AventureiroRepository essencialNomeDto;
    private final OrganizacaoValidator organizacaoValidator;

    public Page<ParticipacaoTudo> ListarTodos(int page, int size) {
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
    public Page<ParticipacaoTudo> ListarPorMissao(Long missaoId, int page, int size) {
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

    public Page<ParticipacaoTudo> ListarPorAventureiro(Long aventureiroId, int page, int size) {
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

    public ParticipacaoMissao Salvar(ParticipacaoMissao participacao) {
        Aventureiro aventureiro = essencialNomeDto.findById(
                        participacao.getAventureiro().getId())
                .orElseThrow(() -> new RuntimeException("Aventureiro não encontrado"));

        Missao missao = missaoRepository.findById(
                        participacao.getMissao().getId())
                .orElseThrow(() -> new RuntimeException("Missão não encontrada"));

        organizacaoValidator.existe(missao.getOrganizacao().getId());

        if (aventureiro.getStatus() == StatusAventureiro.INATIVO) {
            throw new RuntimeException("Aventureiro inativo não pode participar de missões");
        }

        if (missao.getStatus() != StatusMissao.PLANEJADA
                && missao.getStatus() != StatusMissao.EM_ANDAMENTO) {
            throw new RuntimeException("Missão não está em estado compatível para aceitar participantes");
        }

        if (!aventureiro.getOrganizacao().getId()
                .equals(missao.getOrganizacao().getId())) {
            throw new RuntimeException("Aventureiro não pertence à organização da missão");
        }

        if (participacaoRepository.existsByMissaoIdAndAventureiroId(
                missao.getId(), aventureiro.getId())) {
            throw new RuntimeException("Aventureiro já está participando desta missão");
        }

        return participacaoRepository.save(participacao);
    }

    public void Deletar(Long missaoId, Long aventureiroId) {
        ParticipacaoMissaoId id = new ParticipacaoMissaoId(missaoId, aventureiroId);
        if (!participacaoRepository.existsById(id)) {
            throw new RuntimeException("Participação não encontrada");
        }
        participacaoRepository.deleteById(id);
    }

    public void AtualizarPapel(Long missaoId, Long aventureiroId, PapelMissao papel) {
        ParticipacaoMissaoId id = new ParticipacaoMissaoId(missaoId, aventureiroId);
        ParticipacaoMissao participacao = participacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participação não encontrada"));
        participacao.setPapel(papel);
        participacaoRepository.save(participacao);
    }

    public void DefinirMvp(Long missaoId, Long aventureiroId) {
        ParticipacaoMissaoId id = new ParticipacaoMissaoId(missaoId, aventureiroId);
        ParticipacaoMissao participacao = participacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participação não encontrada"));
        participacao.setMvp(true);
        participacaoRepository.save(participacao);
    }

    public void DefinirRecompensa(Long missaoId, Long aventureiroId, Integer ouro) {
        ParticipacaoMissaoId id = new ParticipacaoMissaoId(missaoId, aventureiroId);
        ParticipacaoMissao participacao = participacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participação não encontrada"));
        participacao.setRecompensaOuro(ouro);
        participacaoRepository.save(participacao);
    }
}