# Relatório de Teste de Performance — JSONPlaceholder `/posts`

## Identificação
- **ID do Teste:** PERF-001
- **Data de Execução:** 08/09/2025
- **Responsável:** João Víctor Santos Campos

---

## Configuração do Teste
- **Ferramenta:** K6 v0.51.0
- **Endpoint:** `https://jsonplaceholder.typicode.com/posts`
- **Método:** `GET`
- **Estágios de carga:**
  - 30s → até 10 VUs
  - 30s → até 100 VUs
  - 30s → até 500 VUs
  - 30s → até 1000 VUs
  - 1m → ramp-down para 0 VUs
- **Thresholds definidos:**
  - `http_req_duration`: p95 < 5s
  - `http_req_failed`: taxa < 10%

---

## Resultados Obtidos

### Métricas principais
| Métrica                  | Valor Observado      | Limite Definido | Status |
|--------------------------|----------------------|-----------------|--------|
| Tempo de resposta (média)| ~1.229 ms            | Informativo     | -      |
| Tempo de resposta (med.) | ~913 ms              | Informativo     | -      |
| Tempo de resposta (p95)  | ~3.421 ms            | < 5s            | ✅     |
| Tempo de resposta (máx.) | ~19.137 ms           | Informativo     | ⚠️     |
| Throughput (req/s)       | ~157 req/s           | Informativo     | -      |
| Taxa de erro HTTP        | 0%                   | < 10%           | ✅     |
| Checks falhos (< 5s)     | 343 de 28.365 (1.2%) | < 10%           | ✅     |

---

## Evolução por estágio
- **10 VUs → 100 VUs:** respostas rápidas, sem falhas.  
- **100 VUs → 500 VUs:** aumento natural no tempo médio, mas p95 < 3s.  
- **500 VUs → 1000 VUs:** alguns picos até 19s, porém raros (1,2% das reqs).  
- Nenhuma falha HTTP observada em todo o teste.

---

## Ponto de Falha Identificado
- O endpoint manteve estabilidade **até 1000 VUs**.  
- O **ponto de falha não foi atingido**: thresholds foram respeitados (p95 < 5s e erros < 10%).  
- Pequenos outliers de até 19s foram detectados, mas não comprometeram o resultado geral.

---

## Evidências
- Logs completos: [`results/stress-test-result.txt`](results/stress-test-result.txt)  
- Resumo estruturado: [`results/stress-test-summary.json`](results/stress-test-summary.json)  

---

## Conclusão
- O endpoint `/posts` suportou **1000 usuários simultâneos** dentro dos critérios definidos.  
- Tempo de resposta p95 se manteve < 5s.  
- Não houve falhas HTTP.  
- Conclui-se que a API é **estável sob alta carga** no cenário testado.  
