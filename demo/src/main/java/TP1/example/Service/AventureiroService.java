package TP1.example.Service;

import TP1.example.Domain.Aventureiro;
import TP1.example.Domain.Classe;
import TP1.example.Domain.Companheiro;
import TP1.example.Domain.StatusAventureiro;
import TP1.example.Repository.AventureiroRepository;
import TP1.example.Repository.FakeDataBase;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AventureiroService {
    private final AventureiroRepository repository;

    public List<Aventureiro> ListarTodos() {
        return FakeDataBase.aventureiros;
    }
    public List<Aventureiro> ListarPorClasse(Classe classe) {
        return FakeDataBase.aventureiros.stream()
                .filter(a -> a.getClasse() == classe)
                .collect(Collectors.toList());
    }
    public List<Aventureiro> ListarPorStatus(StatusAventureiro status) {
        return FakeDataBase.aventureiros.stream()
                .filter(a -> a.getStatus() == status)
                .collect(Collectors.toList());

    }
    public List<Aventureiro> ListarPorNivelMaiorQue(int nivel) {
        return FakeDataBase.aventureiros.stream()
                .filter(a -> a.getNivel() > nivel)
                .collect(Collectors.toList());
    }
    public Aventureiro BuscarPorId(Long id) {
        return FakeDataBase.aventureiros.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Aventureiro Não Encontrado"));
    }

    public Aventureiro Salvar(Aventureiro aventureiro) {
        FakeDataBase.aventureiros.add(aventureiro);
        return aventureiro;
    }
    public void Deletar(Long id) {
        FakeDataBase.aventureiros.removeIf(a -> a.getId().equals(id));
    }
    public void AtualizarNome(Long id, String nome) {
        Optional<Aventureiro> aventureiroOpt = FakeDataBase.aventureiros.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
        aventureiroOpt.ifPresent(a -> a.setNome(nome));
    }
    public void AtualizarClasse(Long id, Classe classe) {
        Optional<Aventureiro> aventureiroOpt = FakeDataBase.aventureiros.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
        aventureiroOpt.ifPresent(a -> a.setClasse(classe));
    }
    public void AtualizarNivel(Long id, Integer nivel) {
        Optional<Aventureiro> aventureiroOpt = FakeDataBase.aventureiros.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
        aventureiroOpt.ifPresent(a -> a.setNivel(nivel));
    }
    public void EncerrarVinculo(Long id) {
        Optional<Aventureiro> aventureiroOpt = FakeDataBase.aventureiros.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
        aventureiroOpt.ifPresent(a -> a.setStatus(StatusAventureiro.INATIVO));
    }
    public void RecrutarNovamente(Long id) {
        Optional<Aventureiro> aventureiroOpt = FakeDataBase.aventureiros.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
        aventureiroOpt.ifPresent(a -> a.setStatus(StatusAventureiro.ATIVO));
    }
    public void DefinirComapanheiro(Long avid, Companheiro companheiro) {
        Optional<Aventureiro> aventureiroOpt = FakeDataBase.aventureiros.stream()
                .filter(a -> a.getId().equals(avid))
                .findFirst();
        aventureiroOpt.ifPresent(a -> a.setCompanheiro(companheiro));
    }
    public void RemoverCompanheiro(Long avid) {
        Optional<Aventureiro> aventureiroOpt = FakeDataBase.aventureiros.stream()
                .filter(a -> a.getId().equals(avid))
                .findFirst();
        aventureiroOpt.ifPresent(a -> a.setCompanheiro(null));
    }















//    public List<Aventureiro> ListarTodos() {
//        return repository.findAll();
//    }
//    //TODO Implementar no Controller
//    public List<Aventureiro> ListarPorClasse(Classe classe){
//        return repository.findByClasse(classe);
//    }
//    public List<Aventureiro> ListaPorNivelMaiorQue(Integer nivel){
//        return repository.findByNivelGreaterThan(nivel);
//    }
//    public List<Aventureiro> ListarPorStatus(StatusAventureiro status){
//        return repository.findByStatus(status);
//    }
//    public Aventureiro BuscarPorId(Long id) {
//        return repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
//    }
//    public Aventureiro Salvar(Aventureiro aventureiro) {
//        return repository.save(aventureiro);
//    }
//    public void Deletar(Long id) {
//        repository.deleteById(id);
//    }
//    //TODO Reduzir repetição de código aqui e implementar no controller
//    public void AtualizarNome(Long id, String nome){
//        Optional<Aventureiro> aventureiroOpt = repository.findById(id);
//        if(aventureiroOpt.isPresent()) {
//            Aventureiro aventureiroMod = aventureiroOpt.get();
//            aventureiroMod.setNome(nome);
//            repository.save(aventureiroMod);
//        }
//
//
//    }
//    public void AtualizarClasse(Long id, Classe classe){
//        Optional<Aventureiro> aventureiroOpt = repository.findById(id);
//        if(aventureiroOpt.isPresent()) {
//            Aventureiro aventureiroMod = aventureiroOpt.get();
//            aventureiroMod.setClasse(classe);
//            repository.save(aventureiroMod);
//        }
//
//
//    }
//    public void AtualizarNivel(Long id, Integer nivel){
//        Optional<Aventureiro> aventureiroOpt = repository.findById(id);
//        if(aventureiroOpt.isPresent()) {
//            Aventureiro aventureiroMod = aventureiroOpt.get();
//            aventureiroMod.setNivel(nivel);
//            repository.save(aventureiroMod);
//        }
//
//
//    }
//    public void EncerrarVinculo(Long id){
//        Optional<Aventureiro> aventureiroOpt = repository.findById(id);
//        if(aventureiroOpt.isPresent()) {
//            Aventureiro aventureiroMod = aventureiroOpt.get();
//            aventureiroMod.setStatus(StatusAventureiro.INATIVO);
//            repository.save(aventureiroMod);
//        }
//
//
//    }
//    public void RecrutarNovamente(Long id){
//        Optional<Aventureiro> aventureiroOpt = repository.findById(id);
//        if(aventureiroOpt.isPresent()) {
//            Aventureiro aventureiroMod = aventureiroOpt.get();
//            aventureiroMod.setStatus(StatusAventureiro.ATIVO);
//            repository.save(aventureiroMod);
//        }
//
//
//    }
//    public void DefinirCompanheiro(Long aventureiroid, Companheiro companheiro){
//        Optional<Aventureiro> aventureiroOpt = repository.findById(aventureiroid);
//        if(aventureiroOpt.isPresent()) {
//            Aventureiro aventureiroMod = aventureiroOpt.get();
//            aventureiroMod.setCompanheiro(companheiro);
//            repository.save(aventureiroMod);
//        }
//
//    }
//    public void RemoveCompanheiro(Long aventureiroid, Companheiro companheiro ){
//        Optional<Aventureiro> aventureiroOpt = repository.findById(aventureiroid);
//        if(aventureiroOpt.isPresent()) {
//            Aventureiro aventureiroMod = aventureiroOpt.get();
//            aventureiroMod.setCompanheiro(null);
//            repository.save(aventureiroMod);
//        }
//    }
}
