package TP1.example.Aventura.Controller;

import TP1.example.Aventura.Domain.Missao;
import TP1.example.Aventura.Domain.NiveldePerigo;
import TP1.example.Aventura.Domain.StatusMissao;
import TP1.example.Aventura.Dto.MissaoEspecificaDto;
import TP1.example.Aventura.Dto.MissaoTudo;
import TP1.example.Aventura.Dto.ResultadoMinimoMissaoDto;
import TP1.example.Aventura.Service.MissaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/missoes")
@RequiredArgsConstructor
public class MissaoController {

    private final MissaoService missaoService;

    @GetMapping
    public Page<MissaoTudo> listarTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return missaoService.listarTodos(page, size);
    }

    @GetMapping("/nivel/{nivel}")
    public Page<MissaoTudo> listarPorNivel(
            @PathVariable String nivel,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        NiveldePerigo niveleenum = NiveldePerigo.valueOf(nivel.toUpperCase());
        return missaoService.listarPorNivel(niveleenum, page, size);
    }

    @GetMapping("/status/{status}")
    public Page<MissaoTudo> listarPorStatus(
            @PathVariable StatusMissao status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        StatusMissao statusenum = StatusMissao.valueOf(status.name());
        return missaoService.listarPorStatus(statusenum, page, size);
    }

    @GetMapping("/criacao")
    public Page<ResultadoMinimoMissaoDto> listarPorIntervaloDeCriacao(
            @RequestParam  LocalDateTime inicio,
            @RequestParam  LocalDateTime fim,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Timestamp iniciostamp = Timestamp.valueOf(inicio);
        Timestamp fimstamp = Timestamp.valueOf(fim);
        return missaoService.listarPorIntervaloDeCricao(iniciostamp, fimstamp, page, size);
    }

    @GetMapping("/periodo")
    public Page<ResultadoMinimoMissaoDto> listarPorIntervaloDeComecoeFim(
            @RequestParam  LocalDateTime inicio,
            @RequestParam  LocalDateTime fim,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Timestamp iniciostamp = Timestamp.valueOf(inicio);
        Timestamp fimstamp = Timestamp.valueOf(fim);


        return missaoService.listarPorIntervaloDeComecoeFim(iniciostamp, fimstamp, page, size);
    }

    @GetMapping("/{id}")
    public MissaoEspecificaDto buscarPorId(@PathVariable Long id) {
        return missaoService.buscarEspecificoPorId(id);
    }

    @PostMapping
    public Missao salvar(@RequestBody Missao missao) {
        return missaoService.salvar(missao);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        missaoService.deletar(id);
    }

    @PatchMapping("/{id}/titulo")
    public void atualizarTitulo(
            @PathVariable Long id,
            @RequestParam String titulo) {
        missaoService.atualizarTitulo(id, titulo);
    }

    @PatchMapping("/{id}/status")
    public void atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusMissao status) {
        missaoService.atualizarStatus(id, status);
    }

    @PatchMapping("/{id}/nivel-perigo")
    public void atualizarNivelPerigo(
            @PathVariable Long id,
            @RequestParam NiveldePerigo nivelPerigo) {
        missaoService.atualizarNivelPerigo(id, nivelPerigo);
    }
}