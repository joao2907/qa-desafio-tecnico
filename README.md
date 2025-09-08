# Desafio Técnico QA

Este repositório contém a solução desenvolvida para o **Desafio Técnico de QA**. 
O projeto inclui automação de testes Web e API, testes de performance e documentação de apoio. 
Foram realizados testes **manuais e automatizados** nas seguintes aplicações públicas:

- 🌐 **Web (Automation Testing Demo)** — testes de cadastro, login, formulários, upload, alertas e iframes.  
- 🔗 **API (DummyJSON)** — testes de produtos e usuários (CRUD + validação de contrato).  
- ⚡ **Performance (JSONPlaceholder)** — teste de carga no endpoint `/posts`.  

O projeto inclui:  
- 📋 Plano de Testes detalhado  
- 📝 Casos de teste manuais  
- 🤖 Automação Web e API em Java  
- ⚡ Testes de performance com k6   

---

## 📋 Plano de Testes
O **Plano de Testes completo** está disponível em:  
➡️ Plano de testes.docx

---

## 📂 Estrutura do Repositório
# Estrutura de Diretórios — Projeto `qa-desafio-tecnico`

```plaintext
qa-desafio-tecnico/
│
├── api-testes/                 # Automação API
│
├── web-testes/                 # Automação Web (UI)
│
├── performance-testes/         # Automação de Performance
│   ├── scripts/                # scripts K6
│   │   ├── stress-test-normal.js          # PERF-001
│   │   ├── stress-test-breakpoint.js      # PERF-002
│   │
│   ├── results/                # resultados brutos das execuções
│   │   ├── stress-test-normal-result.txt
│   │   ├── stress-test-normal-summary.json
│   │   ├── stress-test-breakpoint-result.txt
│   │   ├── stress-test-breakpoint-summary.json
│   │
│   ├── reports/                # relatórios interpretados
│   │   ├── REPORT-PERF-001.md
│   │   ├── REPORT-PERF-002.md
│   │
│   └── casos/                  # casos de teste em markdown
│       ├── perf-stress-normal.md     # PERF-001
│       ├── perf-stress-breakpoint.md # PERF-002
│
├── docs/                       # Documentação geral
│   ├── Plano de Testes.docx
│   │
│   ├── Casos de teste/
│   │   ├── api/
│   │   ├── web/
│   │ 
│   │
│   ├── Bugs encontrados/
│   │
│   └── Evidencias/
```
---

## 🚀 Tecnologias e Ferramentas
- **Linguagem**: Java 17  
- **Automação Web**: Selenium + JUnit + Page Object Model  
- **Automação API**: RestAssured + JSON Schema Validator  
- **Performance**: k6  

---

## 📊 Relatórios de Automação API (Allure)

Os testes de API possuem **relatórios visuais** com Allure.

- Para gerar e visualizar localmente:
```bash
mvn clean test
allure serve target/allure-results
```

- Os resultados brutos ficam em: qa-desafio-tecnico/api-testes/target/allure-results/

- O relatório HTML será aberto automaticamente no navegador.

Caso queira apenas gerar a pasta com o relatório:
```bash
allure generate target/allure-results -o target/allure-report
```
---

## ⚡ Relatórios de Testes de Performance

Os testes de performance foram executados com k6 e analisados a partir dos resultados obtidos.

📍 **Caminho dos relatórios interpretados**:
`qa-desafio-tecnico/performance-testes/reports/`

**Resultados principais**:

`REPORT-PERF-001.md`
 → Endpoint `/posts` suportou 1000 usuários simultâneos dentro dos critérios de desempenho.

`REPORT-PERF-002.md`
 → O ponto de falha foi identificado em 2000 VUs.

📌 **Conclusão**: recomenda-se considerar 1000 VUs como limite seguro para este endpoint.