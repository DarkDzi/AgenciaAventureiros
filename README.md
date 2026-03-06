



---

## 📌 Endpoints

### 1️⃣ Listar aventureiros com filtros e paginação

**GET** `/filtrar`

**Query Params:**

| Parâmetro  | Obrigatório | Descrição |
|------------|------------|-----------|
| `classe`  | Não        | Filtra pelo tipo de classe (`GUERREIRO`, `MAGO`, etc.) |
| `status`  | Não        | Filtra pelo status (`ATIVO`, `INATIVO`) |
| `nivelMin`| Não        | Filtra por nível mínimo |
| `page`    | Não        | Página atual (default `0`) |
| `size`    | Não        | Tamanho da página (default `10`) |

**Headers de resposta:**

- `X-Total-Count`: total de registros filtrados  
- `X-Page`: página atual  
- `X-Size`: tamanho da página  
- `X-Total-Pages`: total de páginas

**Exemplo de Requisição:**

``` http:
GET /aventureiros/filtrar?classe=GUERREIRO&nivelMin=10&page=0&size=5
```
---

2️⃣ Buscar aventureiro por ID

GET `/{id}`

200 OK → retorna o aventureiro

404 Not Found → ID não encontrado

Exemplo:

GET `/aventureiros/5`

Resposta:

`{
  "id": 5,
  "nome": "Aventureiro 5",
  "classe": "MAGO",
  "nivel": 8,
  "status": "ATIVO",
  "companheiro": null
}`

---
3️⃣ Criar novo aventureiro

POST `/`

Request Body:

`{
  "nome": "Aventureiro Novo",
  "classe": "GUERREIRO",
  "nivel": 1
}`

201 Created → recurso criado

Header Location: URL do recurso criado

Resposta:

{
  "id": 101,
  "nome": "Aventureiro Novo",
  "classe": "GUERREIRO",
  "nivel": 1,
  "status": "ATIVO",
  "companheiro": null
  
}
---

4️⃣ Atualizar aventureiro parcialmente

PATCH `/{id}`

Atualiza apenas os campos enviados (nome, classe, nivel)

200 OK → recurso atualizado

404 Not Found → ID não encontrado

Exemplo:

PATCH `/aventureiros/5`

Request Body:

`{
  "nome": "Novo Nome",
  "nivel": 15
}`

Resposta:

`{
  "id": 5,
  "nome": "Novo Nome",
  "classe": "MAGO",
  "nivel": 15,
  "status": "ATIVO",
  "companheiro": null
}`


---
5️⃣ Deletar aventureiro

DELETE `/{id}`

204 No Content → deletado

404 Not Found → ID não encontrado
---

6️⃣ Encerrar vínculo

PATCH `/{id}/encerrar`

Altera o status para INATIVO

200 OK → sucesso

404 Not Found → ID não encontrado
---

7️⃣ Recrutar aventureiro novamente

PATCH `/{id}/recrutar`

Altera o status para ATIVO

200 OK → sucesso

404 Not Found → ID não encontrado
---

8️⃣ Definir companheiro

PUT `/{id}/companheiro`

Request Body:

`{
  "nome": "Companheiro 1",
  "tipo": "Familiar"
}`

200 OK → retorna aventureiro atualizado

404 Not Found → ID do aventureiro não encontrado
---

9️⃣ Remover companheiro

DELETE `/{id}/companheiro`

204 No Content → companheiro removido

404 Not Found → ID do aventureiro não encontrado
