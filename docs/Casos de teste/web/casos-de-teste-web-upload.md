# Casos de Teste — Automação Web (Automation Testing Demo)

## UI-WEB-005 — Upload de Arquivo Válido
**Objetivo:** Validar envio de arquivo válido.  
**Pré-condição:** Página de upload acessível.  
**Passos:**
1. Acessar página de upload.
2. Selecionar um arquivo válido (ex.: `.jpg`, `.png` ou `.pdf`).
3. Confirmar upload.
**Resultado esperado:**
- Upload concluído com sucesso.
- Arquivo deve aparecer listado ou confirmação exibida na tela.

---

## UI-WEB-006 — Upload de Arquivo Inválido
**Objetivo:** Garantir que arquivos em formato não permitido sejam rejeitados.  
**Pré-condição:** Página de upload acessível.  
**Passos:**
1. Selecionar arquivo inválido (ex.: `.exe`, `.bat`).
2. Confirmar upload.
**Resultado esperado:**
- Sistema deve rejeitar o arquivo.
- Mensagem de erro apropriada deve ser exibida.

---

UI-WEB-006 — Upload sem selecionar arquivo

Objetivo: Validar envio sem selecionar arquivo.
Pré-condição: Página de upload disponível em https://demo.automationtesting.in/FileUpload.html.
Passos:

Acessar a página de upload.

Não selecionar arquivo.

Clicar em enviar.

Resultado esperado:

Sistema deve exibir mensagem de erro informando que nenhum arquivo foi selecionado