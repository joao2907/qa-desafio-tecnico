# Casos de Teste — Automação Web (Automation Testing Demo)

## UI-WEB-001 — Cadastro de Novo Usuário
**Objetivo:** Validar o fluxo de cadastro de novo usuário no formulário de registro.  
**Pré-condição:** A página de registro deve estar acessível em [https://demo.automationtesting.in/Register.html](https://demo.automationtesting.in/Register.html).  
**Passos:**
1. Acessar a página de registro.
2. Preencher todos os campos obrigatórios (nome, sobrenome, endereço, email, telefone, gênero, hobbies, skills, país, data de nascimento, senha).
3. Submeter o formulário.

**Resultado esperado:**
- O sistema deve validar os dados.
- Usuário deve ser cadastrado com sucesso (redirecionamento para a página de sucesso ou dashboard).  
**Observações:** Validar mensagens de erro quando campos obrigatórios não forem preenchidos.

---

## UI-WEB-002 — Cadastro com Campos Obrigatórios Vazios
**Objetivo:** Garantir que o sistema não aceite cadastro sem preencher campos obrigatórios.  
**Pré-condição:** Página de registro aberta.  
**Passos:**
1. Acessar o formulário de cadastro.
2. Deixar campos obrigatórios vazios (ex.: email, senha).
3. Clicar em "Submit".
**Resultado esperado:**
- Mensagens de erro devem ser exibidas abaixo dos campos obrigatórios.
- Cadastro não deve ser concluído.

---