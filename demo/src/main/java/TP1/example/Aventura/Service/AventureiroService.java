package TP1.example.Aventura.Service;

import TP1.example.Aventura.Domain.*;
import TP1.example.Aventura.Dto.AventureiroTudo;
import TP1.example.Aventura.Dto.EssencialNomeDto;
import TP1.example.Aventura.Dto.PerfilCompletoDto;
import TP1.example.Aventura.Repository.AventureiroRepository;
import TP1.example.Aventura.Repository.ParticipacaoMissaoRepository;
import TP1.example.Aventura.Utils.FormatarTimeStamp;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Comparator;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AventureiroService {

    private final AventureiroRepository repository;
    private final ParticipacaoMissaoRepository participacaoMissaoRepository;




    public Page<AventureiroTudo> listarTodos(int page, int size) {
        return repository.findAll(PageRequest.of(page, size))
                .map(a -> new AventureiroTudo(
                        a.getId(),
                        a.getOrganizacao().getId(),
                        a.getUsuarioResponsavel().getNome(),
                        a.getNome(),
                        a.getClasse(),
                        a.getNivel(),
                        a.getStatus(),
                        a.getCompanheiro() != null ? a.getCompanheiro().getNome() : "Não Informado",
                        FormatarTimeStamp.TimeStampParaString(a.getCriadoem()),
                        FormatarTimeStamp.TimeStampParaString(a.getAtualizadoem())
                        )
                );
    }

    public Page<AventureiroTudo> listarPorClasse(Classe classe, int page, int size) {
        return repository.findByClasse(classe, PageRequest.of(page, size))
                .map(a -> new AventureiroTudo(
                        a.getId(),
                        a.getOrganizacao().getId(),
                        a.getUsuarioResponsavel().getNome(),
                        a.getNome(),
                        a.getClasse(),
                        a.getNivel(),
                        a.getStatus(),
                        a.getCompanheiro() != null ? a.getCompanheiro().getNome() : "Não Informado",
                        FormatarTimeStamp.TimeStampParaString(a.getCriadoem()),
                        FormatarTimeStamp.TimeStampParaString(a.getAtualizadoem())
                )
                );
    }

    public Page<AventureiroTudo> listarPorNivelMaiorQue(Integer nivel, int page, int size) {
        return repository.findByNivelGreaterThan((nivel - 1), PageRequest.of(page, size))
                .map(a -> new AventureiroTudo(
                        a.getId(),
                        a.getOrganizacao().getId(),
                        a.getUsuarioResponsavel().getNome(),
                        a.getNome(),
                        a.getClasse(),
                        a.getNivel(),
                        a.getStatus(),
                        a.getCompanheiro() != null ? a.getCompanheiro().getNome() : "Não Informado",
                        FormatarTimeStamp.TimeStampParaString(a.getCriadoem()),
                        FormatarTimeStamp.TimeStampParaString(a.getAtualizadoem())
                )
        );
    }

    public Page<AventureiroTudo> listarPorStatus(StatusAventureiro status, int page, int size) {
        return repository.findByStatus(status, PageRequest.of(page, size))
                .map(a -> new AventureiroTudo(
                        a.getId(),
                        a.getOrganizacao().getId(),
                        a.getUsuarioResponsavel().getNome(),
                        a.getNome(),
                        a.getClasse(),
                        a.getNivel(),
                        a.getStatus(),
                        a.getCompanheiro() != null ? a.getCompanheiro().getNome() : "Não Informado",
                        FormatarTimeStamp.TimeStampParaString(a.getCriadoem()),
                        FormatarTimeStamp.TimeStampParaString(a.getAtualizadoem())
                )
        );

    }
    public Page<EssencialNomeDto> listarPorNome(String nome, int page, int size, boolean crescente) {
        Sort sort = crescente ? Sort.by("nome").ascending() : Sort.by("nome").descending();
        Pageable pageable = PageRequest.of(page, size,sort);
        return repository.findByNomeContaining(nome,pageable)
                .map(a -> new EssencialNomeDto(
                        a.getId(),
                        a.getNome(),
                        a.getClasse(),
                        a.getNivel(),
                        a.getStatus()
                ));
    }
    public PerfilCompletoDto listarPerfilCompleto(Long id) {
            Aventureiro a = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Aventureiro não encontrado"));
            List<ParticipacaoMissao> participacoes = participacaoMissaoRepository.findByAventureiroId(id);
            Integer totalparticipacaoMissao = participacaoMissaoRepository.countByAventureiroId(id);


        Missao ultimamissao = participacoes.stream()
                .max(Comparator.comparing(ParticipacaoMissao::getCriadoem))
                .map(ParticipacaoMissao::getMissao)
                .orElse(null);




          return new PerfilCompletoDto(
                  a.getId(),
                  a.getNome(),
                  a.getClasse(),
                  a.getNivel(),
                  a.getStatus(),
                  a.getCompanheiro() != null ? a.getCompanheiro().getNome() : "Não Informado",
                  totalparticipacaoMissao,
                  ultimamissao.getTitulo()


          );
    }

    public Aventureiro buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aventureiro não encontrado"));
    }


    public Aventureiro salvar(Aventureiro aventureiro) {
        return repository.save(aventureiro);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    @Transactional()
    public void atualizarNome(Long id, String nome) {
        Aventureiro aventureiro= repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aventureiro não Encotrado"));
        aventureiro.setNome(nome);
        repository.save(aventureiro);
    }
    @Transactional()
    public void atualizarClasse(Long id, Classe classe) {
        Aventureiro aventureiro= repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aventureiro não Encotrado"));
        aventureiro.setClasse(classe);
        repository.save(aventureiro);
    }
    @Transactional()
    public void atualizarNivel(Long id, Integer nivel) {
        Aventureiro aventureiro= repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aventureiro não Encotrado"));
        aventureiro.setNivel(nivel);
        repository.save(aventureiro);
    }
    @Transactional()
    public void encerrarVinculo(Long id) {
        Aventureiro aventureiro= repository.findById(id)
                .orElseThrow(() -> new  EntityNotFoundException("Aventureiro não Encotrado"));
        aventureiro.setStatus(StatusAventureiro.INATIVO);
        repository.save(aventureiro);
    }
    @Transactional()
    public void recrutarNovamente(Long id) {
        Aventureiro aventureiro= repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aventureiro não Encotrado"));
        aventureiro.setStatus(StatusAventureiro.ATIVO);
        repository.save(aventureiro);
    }
    @Transactional()
    public void definirCompanheiro(Long id, Companheiro companheiro) {
        Aventureiro aventureiro= repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aventureiro não Encotrado"));
        companheiro.setAventureiro(aventureiro);
        aventureiro.setCompanheiro(companheiro);
        repository.save(aventureiro);
    }
    @Transactional()
    public void removerCompanheiro(Long id) {
        Aventureiro aventureiro= repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aventureiro não Encotrado"));
        aventureiro.setCompanheiro(null);
        repository.save(aventureiro);
    }
}