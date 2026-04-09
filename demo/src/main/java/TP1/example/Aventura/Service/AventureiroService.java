package TP1.example.Aventura.Service;

import TP1.example.Aventura.Domain.Aventureiro;
import TP1.example.Aventura.Domain.Classe;
import TP1.example.Aventura.Domain.Companheiro;
import TP1.example.Aventura.Domain.StatusAventureiro;
import TP1.example.Aventura.Repository.AventureiroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AventureiroService {

    private final AventureiroRepository repository;

    public List<Aventureiro> ListarTodos() {
        return repository.findAll();
    }

    public List<Aventureiro> ListarPorClasse(Classe classe) {
        return repository.findByClasse(classe);
    }

    public List<Aventureiro> ListarPorNivelMaiorQue(Integer nivel) {
        return repository.findByNivelGreaterThan(nivel);
    }

    public List<Aventureiro> ListarPorStatus(StatusAventureiro status) {
        return repository.findByStatus(status);
    }

    public Aventureiro BuscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aventureiro não encontrado"));
    }

    public Aventureiro Salvar(Aventureiro aventureiro) {
        return repository.save(aventureiro);
    }

    public void Deletar(Long id) {
        repository.deleteById(id);
    }

    public void AtualizarNome(Long id, String nome) {
        Aventureiro aventureiro = BuscarPorId(id);
        aventureiro.setNome(nome);
        repository.save(aventureiro);
    }

    public void AtualizarClasse(Long id, Classe classe) {
        Aventureiro aventureiro = BuscarPorId(id);
        aventureiro.setClasse(classe);
        repository.save(aventureiro);
    }

    public void AtualizarNivel(Long id, Integer nivel) {
        Aventureiro aventureiro = BuscarPorId(id);
        aventureiro.setNivel(nivel);
        repository.save(aventureiro);
    }

    public void EncerrarVinculo(Long id) {
        Aventureiro aventureiro = BuscarPorId(id);
        aventureiro.setStatus(StatusAventureiro.INATIVO);
        repository.save(aventureiro);
    }

    public void RecrutarNovamente(Long id) {
        Aventureiro aventureiro = BuscarPorId(id);
        aventureiro.setStatus(StatusAventureiro.ATIVO);
        repository.save(aventureiro);
    }

    public void DefinirCompanheiro(Long id, Companheiro companheiro) {
        Aventureiro aventureiro = BuscarPorId(id);
        companheiro.setAventureiro(aventureiro);
        aventureiro.setCompanheiro(companheiro);
        repository.save(aventureiro);
    }

    public void RemoverCompanheiro(Long id) {
        Aventureiro aventureiro = BuscarPorId(id);
        aventureiro.setCompanheiro(null);
        repository.save(aventureiro);
    }
}