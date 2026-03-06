package TP1.example.Controller;

import TP1.example.Domain.Aventureiro;
import TP1.example.Service.AventureiroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aventureiros")
public class AventureiroController {
    private final AventureiroService service;

    public AventureiroController(AventureiroService service) {
        this.service = service;
    }
    @GetMapping("/all")
    public List<Aventureiro> ListarTodos() {
        return  service.ListarTodos();
    }
    @GetMapping("/{id}")
    public Aventureiro buscar(@PathVariable Long id) {
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
}
//TODO Fazer pageamento