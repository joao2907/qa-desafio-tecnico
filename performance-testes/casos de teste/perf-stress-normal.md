# Cenário de Teste de Performance — Stress Test no JSONPlaceholder

## Identificação
- **ID:** PERF-001
- **Nome:** Stress Test no endpoint `/posts` da API JSONPlaceholder
- **Tipo de Teste:** Performance — Stress Test
- **Ferramenta:** K6

---

## Objetivo
Avaliar o comportamento do endpoint `/posts` sob carga progressiva de usuários virtuais (VUs), identificando o ponto de falha da aplicação com base em:
- Tempo de resposta (p95)
- Throughput (requisições por segundo)
- Taxa de erro

---

## Escopo
- **Endpoint testado:** `https://jsonplaceholder.typicode.com/posts`
- **Método:** `GET`
- **Usuários Virtuais:** de 10 até 1000 VUs em ramp-up progressivo
- **Métricas monitoradas:**
  - Tempo de resposta (p95)
  - Taxa de erros (% de falhas)
  - Throughput (req/s)

---

## Pré-condições
- K6 instalado e configurado na máquina local.
- Script salvo em: `performance-testes/scripts/stress-test.js`.
- Conexão estável com a internet.
- Pasta `performance-testes/results/` criada para salvar relatórios.

---

## Cenário de Execução
### Configuração de carga
- **Estágios:**
  - 30s → até 10 VUs
  - 30s → até 100 VUs
  - 30s → até 500 VUs
  - 30s → até 1000 VUs
  - 1m → redução para 0 VUs (teardown)

### Passos
1. Estando posicionado no diretório `\qa-desafio-tecnico\performance-testes`, executar o comando:
   ```bash
   k6 run --summary-export=results\stress-test-summary.json scripts\stress-test.js > results\stress-test-result.txt

2. Monitorar a saída do console durante a execução.
3. Verificar os arquivos gerados em `performance-testes/results/`:
   - `stress-test-result.txt` (log completo da execução)
   - `stress-test-summary.json` (resumo estruturado para relatórios)
4. Registrar métricas principais:
   - Tempo de resposta médio
   - Tempo de resposta no p95
   - Throughput (req/s)
   - Taxa de erro
5. Identificar o ponto em que:
   - Tempo de resposta p95 ultrapassa 5 segundos, ou
   - Taxa de erro ultrapassa 10%

### Resultado Esperado
- Até 500 VUs:
  - Tempo de resposta p95 ≤ 5s
  - Taxa de erro < 10%
- Entre 500 e 1000 VUs:
  - Possível degradação progressiva da performance.
  - Identificação clara do ponto de falha.
- Relatórios salvos em `performance-testes/results/`.
- Documento `REPORT.md` preenchido com os resultados observados.

### Evidências

- Arquivos `.txt` e `.json` salvos após execução.

### Observações

- A API JSONPlaceholder é apenas simulada, não tem backend robusto de produção.