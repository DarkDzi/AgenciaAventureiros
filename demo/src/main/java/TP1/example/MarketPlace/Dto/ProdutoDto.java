package TP1.example.MarketPlace.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "guilda_loja")
public class ProdutoDto {
    private String nome;
    private String descricao;
    private String categoria;
    private String raridade;
    private Float preco;
}