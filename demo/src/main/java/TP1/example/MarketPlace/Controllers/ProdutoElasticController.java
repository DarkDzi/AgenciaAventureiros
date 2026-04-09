package TP1.example.MarketPlace.Controllers;
import TP1.example.MarketPlace.Dto.ProdutoDto;
import TP1.example.MarketPlace.Services.ProdutoElasticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoElasticController {

    private final ProdutoElasticService service;

    @GetMapping("/busca/nome")
    public List<ProdutoDto> buscarPorNome(@RequestParam String termo) {
        return service.buscarPorNome(termo);
    }

    @GetMapping("/busca/descricao")
    public List<ProdutoDto> buscarPorDescricao(@RequestParam String termo) {
        return service.buscarPorDescricao(termo);
    }

    @GetMapping("/busca/frase")
    public List<ProdutoDto> buscarPorFrase(@RequestParam String termo) {
        return service.buscarPorFrase(termo);
    }

    @GetMapping("/busca/fuzzy")
    public List<ProdutoDto> buscarFuzzy(@RequestParam String termo) {
        return service.buscarFuzzy(termo);
    }

    @GetMapping("/busca/multicampos")
    public List<ProdutoDto> buscarMultiCampos(@RequestParam String termo) {
        return service.buscarMultiCampos(termo);
    }

    @GetMapping("/busca/com-filtro")
    public List<ProdutoDto> buscarComFiltro(@RequestParam String termo,
                                            @RequestParam String categoria) {
        return service.buscarComFiltro(termo, categoria);
    }

    @GetMapping("/busca/faixa-preco")
    public List<ProdutoDto> buscarPorFaixaPreco(@RequestParam Float min,
                                                @RequestParam Float max) {
        return service.buscarPorFaixaPreco(min, max);
    }

    @GetMapping("/busca/avancada")
    public List<ProdutoDto> buscarAvancada(@RequestParam String categoria,
                                           @RequestParam String raridade,
                                           @RequestParam Float min,
                                           @RequestParam Float max) {
        return service.buscarAvancada(categoria, raridade, min, max);
    }

    @GetMapping("/agregacoes/por-categoria")
    public Map<String, Long> agregacaoPorCategoria() {
        return service.agregacaoPorCategoria();
    }

    @GetMapping("/agregacoes/por-raridade")
    public Map<String, Long> agregacaoPorRaridade() {
        return service.agregacaoPorRaridade();
    }

    @GetMapping("/agregacoes/preco-medio")
    public Double precoMedio() {
        return service.precoMedio();
    }

    @GetMapping("/agregacoes/faixas-preco")
    public Map<String, Long> faixasPreco() {
        return service.faixasPreco();
    }
}
