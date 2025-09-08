# Relatório de Teste de Performance — Identificação do Ponto de Falha

## Identificação
- **ID do Teste:** PERF-002
- **Data de Execução:** 07/09/2025
- **Responsável:** João Víctor Santos Campos

---

## Configuração do Teste
- **Ferramenta:** K6 v0.51.0
- **Endpoint:** `https://jsonplaceholder.typicode.com/posts`
- **Estágios de carga:**
  - 30s → até 100 VUs
  - 30s → até 500 VUs
  - 30s → até 1000 VUs
  - 30s → até 2000 VUs
  - 30s → até 3000 VUs
  - 30s → até 5000 VUs
  - 1m → ramp-down para 0 VUs
- **Thresholds definidos:**
  - `http_req_duration`: p95 < 5s
  - `http_req_failed`: taxa < 10%

---

## Resultados Obtidos

### Métricas principais
| Estágio | VUs Ativos | p95 (ms) | Taxa de Erros (%) | Status |
|---------|-----------:|----------:|------------------:|--------|
| 100     |  ~<2000    | < 2000   | 0%–1%             | ✅     |
| 500     |  ~<3000    | < 3000   | 2%                | ✅     |
| 1000    |  ~4000     | ~4000    | 5%                | ✅/⚠️  |
| 2000    |  ~6000     | > 5000   | 12%               | ❌     |
| 3000    |  ~7000     | ~7000    | 18%               | ❌     |
| 5000    |  ~7800     | ~21%     | ❌     |

---

## Ponto de Falha Identificado
- O ponto de falha ocorreu no estágio de **2000 VUs**, quando:
  - O **tempo de resposta p95 ultrapassou 5s** (≈ 6s+), e
  - A **taxa de erros superou 10%**.
- Até **1000 VUs**, o sistema manteve-se estável dentro dos thresholds.

---

## Evidências
- Logs completos: [`results/stress-test-breakpoint-result.txt`](results/stress-test-breakpoint-result.txt)  
- Resumo estruturado: [`results/stress-test-breakpoint-summary.json`](results/stress-test-breakpoint-summary.json)

---

## Conclusão
- O endpoint `/posts` suportou carga estável até **1000 VUs**.  
- O ponto de falha foi atingido em **2000 VUs**.  
- Após esse nível, a API apresentou degradação significativa de desempenho:  
  - **Latência elevada (p95 ~7,8s em 5000 VUs)**  
  - **Taxa de erro 21%**  
- Portanto, recomenda-se considerar **1000 VUs** como limite seguro para esse endpoint.
