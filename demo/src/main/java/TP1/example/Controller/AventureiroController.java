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
    @GetMapping
    public List<Aventureiro> listar() {
        return  service.ListarTodos();
    }
    @GetMapping("/{id}")
    public Aventureiro buscar(@PathVariable Long id) {
        return service.BuscarPorId(id);
    }

    @PostMapping
    public Aventureiro criar(@RequestBody Aventureiro aventureiro) {
        return service.Salvar(aventureiro);
    }
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.Deletar(id);
    }
}
