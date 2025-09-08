# UI-WEB-BUG-001 — Dropdown "Country" não exibe opções

**Severidade:** Alta  
**Prioridade:** Alta  
**Status:** Aberto  

## Descrição
Durante o fluxo de cadastro de usuário em [https://demo.automationtesting.in/Register.html](https://demo.automationtesting.in/Register.html), ao clicar no campo **Country**, o sistema não exibe a lista de opções disponíveis.  

Isso impede que o campo seja preenchido, resultando em falha na validação do formulário e impossibilitando a conclusão do cadastro.  

## Passos para Reproduzir
1. Acessar [https://demo.automationtesting.in/Register.html](https://demo.automationtesting.in/Register.html).  
2. Preencher os campos obrigatórios até chegar em **Country**.  
3. Clicar no campo **Country**.  

## Resultado Atual
- O dropdown não exibe nenhuma opção.  
- Ao clicar em **Submit**, o sistema exige o preenchimento do campo "Country", mas não há como atender à validação.  

## Resultado Esperado
- O campo **Country** deve exibir uma lista de opções ao ser clicado.  
- O usuário deve conseguir selecionar um país e concluir o cadastro com sucesso.  

## Evidência
- **Screenshot:** (armazenado em `qa-desafio-tecnico\docs\Bugs\web\evidência`)  
- **Automação:** Cenário `UI-WEB-001` falha no passo `selectCountry("Australia")` devido a esse bug.  

---