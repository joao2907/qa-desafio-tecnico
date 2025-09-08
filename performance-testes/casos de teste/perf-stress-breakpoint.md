# Cenário de Teste de Performance — Identificação do Ponto de Falha

## Identificação
- **ID:** PERF-002
- **Nome:** Stress Test até falha no endpoint `/posts`
- **Tipo de Teste:** Performance — Stress Test (Breakpoint)
- **Ferramenta:** K6

---

## Objetivo
Forçar a API JSONPlaceholder (`/posts`) até atingir o ponto de falha, definido como:
- Tempo de resposta p95 > 5s, ou
- Taxa de erro (falhas HTTP) > 10%

---

## Escopo
- **Endpoint testado:** `https://jsonplaceholder.typicode.com/posts`
- **Método:** `GET`
- **Usuários Virtuais:** escalada progressiva de 100 até 5000 VUs
- **Métricas monitoradas:**
  - Tempo de resposta (p95)
  - Taxa de erros (%)
  - Throughput (req/s)

---

## Pré-condições
- K6 instalado e configurado.
- Script salvo em: `performance-testes/scripts/stress-test-breakpoint.js`.
- Pasta `performance-testes/results/` criada para salvar relatórios.

---

## Cenário de Execução
### Configuração de carga
- **Estágios:**
  - 30s → até 100 VUs
  - 30s → até 500 VUs
  - 30s → até 1000 VUs
  - 30s → até 2000 VUs
  - 30s → até 3000 VUs
  - 30s → até 5000 VUs
  - 1m → ramp-down para 0 VUs

### Passos
1. Estando posicionado no diretório `\qa-desafio-tecnico\performance-testes`, executar o comando:
   ```bash
   k6 run --summary-export=results\stress-test-breakpoint-summary.json scripts\stress-test-breakpoint.js > results\stress-test-breakpoint-result.txt

2. Monitorar métricas no console.
3. Verificar arquivos salvos em `performance-testes/results/`:
  - `stress-test-breakpoint-result.txt`
  - `stress-test-breakpoint-summary.json`
4. Registrar em qual estágio os thresholds foram quebrados:
  - p95 > 5s
  - Erros HTTP > 10%
5. Documentar o ponto de falha no relatório final (`REPORT.md`).

### Resultado Esperado

- Até certo número de usuários, a API deve manter:
   - p95 < 5s
   - Taxa de erro < 10%
- A partir de uma carga mais alta (estimada entre 2000 e 5000 VUs), o endpoint deve falhar:
   - p95 > 5s, e/ou
   - Taxa de erro > 10%
- O relatório deve indicar claramente o ponto de falha.

### Evidências

- Arquivos .txt e .json exportados.
- Conclusão registrada no `REPORT.md`.

### Observações

- JSONPlaceholder é uma API pública simulada; falhas podem ocorrer antes por limitações de rede ou infraestrutura externa.
-  objetivo é demonstrar capacidade de identificar o ponto de falha, não avaliar robustez real da API.