package TP1.example.Controller;

import TP1.example.Domain.Aventureiro;
import TP1.example.Domain.Classe;
import TP1.example.Domain.Companheiro;
import TP1.example.Domain.StatusAventureiro;
import TP1.example.Service.AventureiroService;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/filtrar")
    public ResponseEntity<List<Aventureiro>> ListarFiltradoComPaginacao(
            @RequestParam(required = false) Classe classe,
            @RequestParam(required = false) StatusAventureiro status,
            @RequestParam(required = false) Integer nivelMin,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        List<Aventureiro> list = service.listarComPaginacao(classe,status,nivelMin,page,size);
        int total = list.size();
        int totalpages = (int)Math.ceil((double) total/size);
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(total))
                .header("X-Page", String.valueOf(page))
                .header("X-Size", String.valueOf(size))
                .header("X-Total-Pages", String.valueOf(totalpages))
                .body(list);
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
