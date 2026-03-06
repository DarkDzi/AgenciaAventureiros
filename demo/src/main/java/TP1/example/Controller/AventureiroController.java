package TP1.example.Controller;

import TP1.example.Domain.Aventureiro;
import TP1.example.Domain.Classe;
import TP1.example.Domain.Companheiro;
import TP1.example.Domain.StatusAventureiro;
import TP1.example.Service.AventureiroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aventureiros")
public class AventureiroController {
    //TODO Fazer pageamento
    private final AventureiroService service;

    public AventureiroController(AventureiroService service) {
        this.service = service;
    }
    @GetMapping("/all")
    public List<Aventureiro> ListarTodos() {
        return  service.ListarTodos();
    }
    @GetMapping("/classe={classe}")
    public List<Aventureiro> ListarPorClasse(@PathVariable Classe classe) {
        return  service.ListarPorClasse(classe);
    }
    @GetMapping("/status={status}")
    public List<Aventureiro> ListarPorStatus(@PathVariable StatusAventureiro status) {
        return  service.ListarPorStatus(status);
    }
    @GetMapping("/nivel>{nivel}")
    public List<Aventureiro> ListarPorClasse(@PathVariable Integer nivel) {
        return  service.ListarPorNivelMaiorQue(nivel);
    }
    @GetMapping("/{id}")
    public Aventureiro buscarporId(@PathVariable Long id) {
        return service.BuscarPorId(id);
    }

    @PostMapping
    public Aventureiro registrar(@RequestBody Aventureiro aventureiro) {
        System.out.print("Aventureiro: " + aventureiro.getNome() + " Registrado!" );
        return service.Salvar(aventureiro);
    }
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.Deletar(id);
    }
    @PatchMapping("/{id}")
    public Aventureiro atualizar(@PathVariable Long id,@RequestBody Aventureiro novosdados) {
        if(novosdados.getNome() != null) service.AtualizarNome(id, novosdados.getNome());
        if(novosdados.getClasse() != null) service.AtualizarClasse(id, novosdados.getClasse());
        if(novosdados.getNivel() != null) service.AtualizarNivel(id, novosdados.getNivel());
        return service.BuscarPorId(id);
    }
    @PatchMapping("/{id}/encerrar")
    public void encerrar(@PathVariable Long id) {
        service.EncerrarVinculo(id);
    }
    @PatchMapping("/{id}/recrutar")
    public void recrutar(@PathVariable Long id) {
        service.RecrutarNovamente(id);
    }
    @PutMapping("{id}/companheiro")
    public void definircompanheiro(@PathVariable Long id, @RequestBody Companheiro companheiro) {
        service.DefinirComapanheiro(id, companheiro);
    }
    @DeleteMapping("{id}/companheiro")
    public void removercompanheiro(@PathVariable Long id) {
        service.RemoverCompanheiro(id);
    }
}
