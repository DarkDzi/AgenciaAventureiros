package TP1.example.Aventura.Controller;

import TP1.example.Aventura.Domain.Missao;
import TP1.example.Aventura.Domain.NiveldePerigo;
import TP1.example.Aventura.Domain.StatusMissao;
import TP1.example.Aventura.Dto.MissaoEspecificaDto;
import TP1.example.Aventura.Dto.ResultadoMinimoMissaoDto;
import TP1.example.Aventura.Service.MissaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/missoes")
@RequiredArgsConstructor
public class MissaoController {

    private final MissaoService missaoService;

    @GetMapping
    public Page<Missao> listarTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return missaoService.ListarTodos(page, size);
    }

    @GetMapping("/nivel/{nivel}")
    public Page<Missao> listarPorNivel(
            @PathVariable NiveldePerigo nivel,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return missaoService.ListarPorNivel(nivel, page, size);
    }

    @GetMapping("/status/{status}")
    public Page<Missao> listarPorStatus(
            @PathVariable StatusMissao status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return missaoService.ListarPorStatus(status, page, size);
    }

    @GetMapping("/criacao")
    public Page<ResultadoMinimoMissaoDto> listarPorIntervaloDeCriacao(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return missaoService.ListarPorIntervaloDeCricao(inicio, fim, page, size);
    }

    @GetMapping("/periodo")
    public Page<ResultadoMinimoMissaoDto> listarPorIntervaloDeComecoeFim(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return missaoService.ListarPorIntervaloDeComecoeFim(inicio, fim, page, size);
    }

    @GetMapping("/{id}")
    public MissaoEspecificaDto buscarPorId(@PathVariable Long id) {
        return missaoService.BuscarEspecificoPorId(id);
    }

    @PostMapping
    public Missao salvar(@RequestBody Missao missao) {
        return missaoService.Salvar(missao);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        missaoService.Deletar(id);
    }

    @PatchMapping("/{id}/titulo")
    public void atualizarTitulo(
            @PathVariable Long id,
            @RequestParam String titulo) {
        missaoService.AtualizarTitulo(id, titulo);
    }

    @PatchMapping("/{id}/status")
    public void atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusMissao status) {
        missaoService.AtualizarStatus(id, status);
    }

    @PatchMapping("/{id}/nivel-perigo")
    public void atualizarNivelPerigo(
            @PathVariable Long id,
            @RequestParam NiveldePerigo nivelPerigo) {
        missaoService.AtualizarNivelPerigo(id, nivelPerigo);
    }
}