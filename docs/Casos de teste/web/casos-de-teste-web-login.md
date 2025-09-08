# Casos de Teste — Automação Web (Automation Testing Demo)

## UI-WEB-003 — Login com Credenciais Válidas (Bloqueado)
**Objetivo:** Validar login com credenciais válidas.  
**Pré-condição:** Usuário já cadastrado. Página de login acessível.  
**Passos:**
1. Acessar a página de login.
2. Informar usuário e senha corretos.
3. Clicar em "Login".

**Resultado esperado:**
- Sistema deve autenticar e redirecionar o usuário para a página inicial.
- **Observações:** Atualmente o fluxo de cadastro está bloqueado devido ao BUG UI-WEB-BUG-001 (campo Country não exibe opções).
- **Impacto:** Cenário não pode ser executado de ponta a ponta até a correção do bug.
- **Status atual:** Bloqueado (Blocked).
---

## UI-WEB-004 — Login com Credenciais Inválidas
**Objetivo:** Validar mensagem de erro quando usuário ou senha forem inválidos.  
**Pré-condição:** Página de login acessível.  
**Passos:**
1. Acessar a página de login.
2. Informar usuário e senha inválidos.
3. Clicar em "Login".

**Resultado esperado:**
- Sistema deve rejeitar o login.
- Mensagem de erro clara deve ser exibida.

---