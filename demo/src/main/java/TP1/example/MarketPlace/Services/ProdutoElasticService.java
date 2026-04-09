package TP1.example.MarketPlace.Services;

import TP1.example.MarketPlace.Dto.ProdutoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchAggregations;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.json.JsonData;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoElasticService {

    private final ElasticsearchOperations operations;

    public List<ProdutoDto> buscarPorNome(String termo) {
        Query query = Query.of(q -> q
                .match(m -> m
                        .field("nome")
                        .query(termo)
                )
        );
        return executarBusca(query);
    }

    public List<ProdutoDto> buscarPorDescricao(String termo) {
        Query query = Query.of(q -> q
                .match(m -> m
                        .field("descricao")
                        .query(termo)
                )
        );
        return executarBusca(query);
    }

    public List<ProdutoDto> buscarPorFrase(String termo) {
        Query query = Query.of(q -> q
                .matchPhrase(m -> m
                        .field("descricao")
                        .query(termo)
                )
        );
        return executarBusca(query);
    }

    public List<ProdutoDto> buscarFuzzy(String termo) {
        Query query = Query.of(q -> q
                .fuzzy(f -> f
                        .field("nome")
                        .value(termo)
                        .fuzziness("AUTO")
                )
        );
        return executarBusca(query);
    }

    public List<ProdutoDto> buscarMultiCampos(String termo) {
        Query query = Query.of(q -> q
                .multiMatch(m -> m
                        .fields("nome", "descricao")
                        .query(termo)
                )
        );
        return executarBusca(query);
    }

    public List<ProdutoDto> buscarComFiltro(String termo, String categoria) {
        Query query = Query.of(q -> q
                .bool(b -> b
                        .must(m -> m
                                .match(ma -> ma
                                        .field("descricao")
                                        .query(termo)
                                )
                        )
                        .filter(f -> f
                                .term(t -> t
                                        .field("categoria")
                                        .value(categoria)
                                )
                        )
                )
        );
        return executarBusca(query);
    }

    public List<ProdutoDto> buscarPorFaixaPreco(Float min, Float max) {
        Query query = Query.of(q -> q
                .range(r -> r
                        .field("preco")
                        .gte(JsonData.of(min))
                        .lte(JsonData.of(max))
                )
        );
        return executarBusca(query);
    }

    public List<ProdutoDto> buscarAvancada(String categoria, String raridade,
                                           Float min, Float max) {
        Query query = Query.of(q -> q
                .bool(b -> b
                        .filter(f -> f
                                .term(t -> t
                                        .field("categoria")
                                        .value(categoria)
                                )
                        )
                        .filter(f -> f
                                .term(t -> t
                                        .field("raridade")
                                        .value(raridade)
                                )
                        )
                        .filter(f -> f
                                .range(r -> r
                                        .field("preco")
                                        .gte(JsonData.of(min))
                                        .lte(JsonData.of(max))
                                )
                        )
                )
        );
        return executarBusca(query);
    }

    public Map<String, Long> agregacaoPorCategoria() {
        return executarAgregacaoTerms("categoria", "por_categoria");
    }

    public Map<String, Long> agregacaoPorRaridade() {
        return executarAgregacaoTerms("raridade", "por_raridade");
    }

    public Double precoMedio() {
        Aggregation agg = Aggregation.of(a -> a
                .avg(avg -> avg.field("preco"))
        );
        NativeQuery nativeQuery = NativeQuery.builder()
                .withAggregation("preco_medio", agg)
                .withMaxResults(0)
                .build();

        SearchHits<ProdutoDto> hits = operations.search(nativeQuery, ProdutoDto.class);
        ElasticsearchAggregations aggregations = (ElasticsearchAggregations) hits.getAggregations();

        return aggregations.get("preco_medio")
                .aggregation()
                .getAggregate()
                .avg()
                .value();
    }

    public Map<String, Long> faixasPreco() {
        Aggregation agg = Aggregation.of(a -> a
                .range(r -> r
                        .field("preco")
                        .ranges(f -> f.to("100.0"))
                        .ranges(f -> f.from("100.0").to("300.0"))
                        .ranges(f -> f.from("300.0").to("700.0"))
                        .ranges(f -> f.from("700.0"))
                )
        );
        NativeQuery nativeQuery = NativeQuery.builder()
                .withAggregation("faixas", agg)
                .withMaxResults(0)
                .build();

        SearchHits<ProdutoDto> hits = operations.search(nativeQuery, ProdutoDto.class);
        ElasticsearchAggregations aggregations = (ElasticsearchAggregations) hits.getAggregations();

        Map<String, Long> resultado = new HashMap<>();
        var buckets = aggregations.get("faixas")
                .aggregation()
                .getAggregate()
                .range()
                .buckets()
                .array();

        for (var bucket : buckets) {
            String key = bucket.key();
            resultado.put(key, bucket.docCount());
        }

        return resultado;
    }

    private List<ProdutoDto> executarBusca(Query query) {
        NativeQuery nativeQuery = NativeQuery.builder()
                .withQuery(query)
                .build();
        SearchHits<ProdutoDto> hits = operations.search(nativeQuery, ProdutoDto.class);
        return hits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    private Map<String, Long> executarAgregacaoTerms(String campo, String nomeAgg) {
        Aggregation agg = Aggregation.of(a -> a
                .terms(t -> t.field(campo))
        );
        NativeQuery nativeQuery = NativeQuery.builder()
                .withAggregation(nomeAgg, agg)
                .withMaxResults(0)
                .build();

        SearchHits<ProdutoDto> hits = operations.search(nativeQuery, ProdutoDto.class);
        ElasticsearchAggregations aggregations = (ElasticsearchAggregations) hits.getAggregations();

        Map<String, Long> resultado = new HashMap<>();
        aggregations.get(nomeAgg)
                .aggregation()
                .getAggregate()
                .sterms()
                .buckets()
                .array()
                .forEach(b -> resultado.put(b.key().stringValue(), b.docCount()));
        return resultado;
    }
}