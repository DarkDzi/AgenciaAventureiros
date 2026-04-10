# 🗡️ Guilda dos Aventureiros
 
API REST desenvolvida com **Spring Boot**, **PostgreSQL** e **Elasticsearch** para gerenciamento de aventureiros, missões e um marketplace.
 
---
 
## 🚀 Como rodar o projeto
 
### Pré-requisitos
 
- [Docker](https://www.docker.com/) instalado
- [Docker Compose](https://docs.docker.com/compose/) instalado
 
### Subindo o projeto
 
Na TP1_AgenciaAventureiros/demo, execute:

 
```bash
docker compose up -d db
docker exec -it TP2_SpringBoot bash
psql -U postgres -c "ALTER USER postgres WITH PASSWORD '123';"
docker compose up -d --no-deps app elastic
```
 
Isso irá subir automaticamente:
- 🗄️ **PostgreSQL** — banco de dados principal (porta `5432`)
- 🔍 **Elasticsearch** — motor de busca (porta `9200`)
- ☕ **Aplicação Spring Boot** — API REST (porta `9090`)
 
> A aplicação aguarda o banco de dados estar saudável antes de iniciar.
 
### Derrubando o projeto
 
```bash
docker compose down
```
 
---
 
## 📡 Endpoints
 
Base URL: `http://localhost:9090`
 
---
 
### 🧙 Aventureiros — `/aventureiros`
 
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/aventureiros` | Lista todos os aventureiros (paginado) |
| GET | `/aventureiros/{id}` | Busca aventureiro por ID |
| GET | `/aventureiros/perfil/{id}` | Retorna perfil completo do aventureiro |
| GET | `/aventureiros/classe/{classe}` | Filtra por classe (paginado) |
| GET | `/aventureiros/status/{status}` | Filtra por status (paginado) |
| GET | `/aventureiros/nivel_min/{nivel}` | Filtra por nível mínimo (paginado) |
| GET | `/aventureiros/nome/{nome}` | Busca por nome com ordenação (paginado) |
| POST | `/aventureiros` | Registra novo aventureiro |
| PATCH | `/aventureiros/{id}` | Atualiza dados do aventureiro |
| PATCH | `/aventureiros/{id}/encerrar` | Encerra vínculo do aventureiro |
| PATCH | `/aventureiros/{id}/recrutar` | Recruta aventureiro novamente |
| PUT | `/aventureiros/{id}/companheiro` | Define companheiro do aventureiro |
| DELETE | `/aventureiros/{id}/companheiro` | Remove companheiro |
| DELETE | `/aventureiros/{id}` | Remove aventureiro |
 
**Parâmetros de paginação** (disponíveis nos endpoints paginados):
 
| Parâmetro | Padrão | Descrição |
|-----------|--------|-----------|
| `page` | `0` | Número da página |
| `size` | `10` | Itens por página |
| `crescente` | `true` | Ordenação crescente (apenas em `/nome`) |
 
---
 
### ⚔️ Missões — `/missoes`
 
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/missoes` | Lista todas as missões (paginado) |
| GET | `/missoes/{id}` | Busca missão por ID |
| GET | `/missoes/nivel/{nivel}` | Filtra por nível de perigo (paginado) |
| GET | `/missoes/status/{status}` | Filtra por status (paginado) |
| GET | `/missoes/criacao?inicio=&fim=` | Filtra por intervalo de criação (paginado) |
| GET | `/missoes/periodo?inicio=&fim=` | Filtra por intervalo de início e fim (paginado) |
| GET | `/missoes/top15dias` | Top missões dos últimos 15 dias |
| POST | `/missoes` | Cria nova missão |
| PATCH | `/missoes/{id}/titulo` | Atualiza título da missão |
| PATCH | `/missoes/{id}/status` | Atualiza status da missão |
| PATCH | `/missoes/{id}/nivel-perigo` | Atualiza nível de perigo |
| DELETE | `/missoes/{id}` | Remove missão |
 
**Formato de data:** `ISO 8601` — ex: `2024-01-15T10:00:00`
 
---
 
### 🤝 Participação em Missões — `/participacaomissao`
 
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/participacaomissao/missao/{missaoId}` | Lista participantes de uma missão |
| GET | `/participacaomissao/aventureiro/{aventureiroId}` | Lista missões de um aventureiro |
| POST | `/participacaomissao` | Registra participação |
| PATCH | `/participacaomissao/{missaoId}/{aventureiroId}/papel` | Atualiza papel na missão |
| PATCH | `/participacaomissao/{missaoId}/{aventureiroId}/mvp` | Define MVP da missão |
| PATCH | `/participacaomissao/{missaoId}/{aventureiroId}/recompensa` | Define recompensa em ouro |
| DELETE | `/participacaomissao/{missaoId}/{aventureiroId}` | Remove participação |
 
---
 
### 📊 Relatórios — `/relatorios`
 
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/relatorios/ranking?inicio=&fim=&statusMissao=` | Ranking de participação por período |
| GET | `/relatorios/missoes?inicio=&fim=` | Relatório de missões por período |
 
---
 
### 🛒 Marketplace (Elasticsearch) — `/produtos`
 
#### Buscas
 
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/produtos/busca/nome?termo=` | Busca por nome do produto |
| GET | `/produtos/busca/descricao?termo=` | Busca por descrição |
| GET | `/produtos/busca/frase?termo=` | Busca por frase exata |
| GET | `/produtos/busca/fuzzy?termo=` | Busca com tolerância a erros de digitação |
| GET | `/produtos/busca/multicampos?termo=` | Busca em múltiplos campos |
| GET | `/produtos/busca/com-filtro?termo=&categoria=` | Busca com filtro por categoria |
| GET | `/produtos/busca/faixa-preco?min=&max=` | Filtra por faixa de preço |
| GET | `/produtos/busca/avancada?categoria=&raridade=&min=&max=` | Busca avançada combinada |
 
#### Agregações
 
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/produtos/agregacoes/por-categoria` | Contagem de produtos por categoria |
| GET | `/produtos/agregacoes/por-raridade` | Contagem de produtos por raridade |
| GET | `/produtos/agregacoes/preco-medio` | Preço médio dos produtos |
| GET | `/produtos/agregacoes/faixas-preco` | Distribuição por faixas de preço |
 
---
 
## 🛠️ Tecnologias
 
- Java 17
- Spring Boot
- Spring Data JPA
- Spring Data Elasticsearch
- PostgreSQL
- Elasticsearch
- Docker / Docker Compose
- Lombok
