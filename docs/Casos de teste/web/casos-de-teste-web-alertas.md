UI-WEB-007 — Interação com alerta simples

Objetivo: Validar exibição e fechamento de alerta simples.
Pré-condição: Página de alertas disponível em https://demo.automationtesting.in/Alerts.html.
Passos:

Acessar a página de alertas.

Clicar no botão que dispara um alert().

Validar exibição da mensagem.

Clicar em OK.

Resultado esperado:

Alerta deve ser exibido com a mensagem correta.

Após clicar em OK, alerta deve ser fechado.

UI-WEB-008 — Interação com alerta de confirmação

Objetivo: Validar comportamento de confirm() aceitando e rejeitando.
Pré-condição: Página de alertas disponível em https://demo.automationtesting.in/Alerts.html.
Passos:

Acessar a página de alertas.

Clicar no botão que dispara um confirm().

Clicar em OK.

Repetir o fluxo e clicar em Cancelar.

Resultado esperado:

Quando aceito, página deve exibir mensagem indicando confirmação positiva.

Quando cancelado, página deve exibir mensagem indicando rejeição.

UI-WEB-009 — Interação com alerta de prompt

Objetivo: Validar envio de texto em prompt().
Pré-condição: Página de alertas disponível em https://demo.automationtesting.in/Alerts.html.
Passos:

Acessar a página de alertas.

Clicar no botão que dispara um prompt().

Digitar um texto no campo.

Clicar em OK.

Resultado esperado:

Página deve exibir a mensagem concatenada com o texto informado.