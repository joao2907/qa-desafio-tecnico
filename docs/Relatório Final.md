# 📑 Relatório Final — Desafio Técnico de QA

Este relatório apresenta as atividades realizadas durante o desafio técnico, contemplando **testes de API (DummyJSON)**, **testes Web (Automation Testing Demo)** e **testes de performance (JSONPlaceholder)**.  

---

## 🔹 Parte 1 — Testes de API (DummyJSON)

### 1. Exploração manual
- Realizei testes manuais no **Postman**, com o objetivo de entender os **endpoints**, formatos de resposta e limitações.  
- Validei endpoints relacionados a **CRUD de produtos e usuários**.  
- Efetuei checagem de **contratos (JSON Schema)** e consistência de dados.

### 2. Definição de casos de teste
- Casos de teste manuais documentados com critérios claros.  
- Arquivos:
  - `qa-desafio-tecnico/docs/Casos de teste/api/casos-de-teste-api-produtos.md`
  - `qa-desafio-tecnico/docs/Casos de teste/api/casos-de-teste-api-usuarios.md`

### 3. Schemas JSON
- Geração e definição de **schemas JSON** para validação de contrato.  
- Arquivos armazenados em:  
  - `qa-desafio-tecnico/evidencias/api/schemas`

### 4. Automação
- Criação de automação dos casos de teste utilizando **RestAssured + JUnit**.  

### 5. Relatórios
- Integração da automação com **Allure Reports**, permitindo visualização detalhada dos resultados.

---

## 🔹 Parte 2 — Testes Web (Automation Testing Demo)

### 1. Exploração manual
- Explorei a aplicação **https://demo.automationtesting.in/** para entender as funcionalidades a serem validadas.  
- Funcionalidades mapeadas:  
  - Cadastro de usuário (formulário)  
  - Login de usuário  
  - Interação com alertas  
  - Upload de arquivos  
  - Manipulação de frames  

### 2. Casos de teste
- Casos de teste manuais documentados.  
- Arquivos:  
  - `qa-desafio-tecnico/docs/Casos de teste/web/casos-de-teste-web-cadastro.md`  
  - `qa-desafio-tecnico/docs/Casos de teste/web/casos-de-teste-web-login.md`  
  - `qa-desafio-tecnico/docs/Casos de teste/web/casos-de-teste-web-alertas.md`  
  - `qa-desafio-tecnico/docs/Casos de teste/web/casos-de-teste-web-upload.md`  
  - `qa-desafio-tecnico/docs/Casos de teste/web/casos-de-teste-web-frames.md`

### 3. Identificação de bug
- Identificado **defeito no formulário de cadastro**: o campo **Country** não exibe as opções no dropdown.  
- Isso impede o fluxo completo de cadastro e bloqueia o teste de login (já que depende de cadastro prévio).  
- Report do bug criado em:  
  - `qa-desafio-tecnico/docs/Bugs encontrados/web/UI-WEB-BUG-001-country-dropdown.md`  
- Evidências anexadas (screenshot do problema).

### 4. Automação
- Criação da automação de testes com **Selenium WebDriver + JUnit**, aplicando o padrão **Page Objects**.  
- Implementados cenários de cadastro, login, upload, alertas e frames.  
- Observações importantes:  
  - O teste automatizado de **cadastro** não pode ser executado devido ao bug reportado.  
  - O teste de **login** também não é viável, pois depende do cadastro.  
  - Isso foi destacado diretamente no código da automação.

---

## 🔹 Parte 3 — Testes de Performance (JSONPlaceholder)

### 1. Casos de teste criados
- **REPORT-PERF-001** → Validar endpoint `/posts` com **1000 usuários simultâneos**.  
  - Resultado: Endpoint suportou a carga dentro dos critérios definidos.  
- **REPORT-PERF-002** → Estressar endpoint `/posts` até falha.  
  - Resultado: Ponto de falha atingido em **2000 VUs**.  

### 2. Relatórios de performance
- Documentação dos resultados em arquivos Markdown.  
- Conclusão:  
  - O endpoint `/posts` suporta **1000 VUs** de forma estável.  
  - Recomenda-se considerar **1000 usuários simultâneos** como **limite seguro** para esse endpoint.

---

## 📌 Conclusões Gerais

- Foram contemplados **testes manuais, automatizados e de performance** em diferentes contextos (API, Web e Performance).  
- Todos os cenários foram documentados em **casos de teste estruturados**.  
- Evidências e relatórios foram gerados e armazenados no repositório.  
- Identificação de um **bug crítico na aplicação web** e documentação adequada do mesmo.  
- A automação foi estruturada de forma escalável (**Page Objects, RestAssured, Allure Reports, K6**).  

➡️ Este conjunto de entregas demonstra **cobertura ampla de QA**, envolvendo desde a análise manual até a automação e performance, seguindo boas práticas de documentação e versionamento.

---
