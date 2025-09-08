# Casos de Teste — API Usuários (DummyJSON)

## API-USER-001 — Listar todos os usuários
**Objetivo:** Validar a listagem de usuários.  
**Pré-condição:** `baseUrl = https://dummyjson.com` configurado.

**Passos:**  
1. Executar `GET /users`.  
2. Observar a resposta JSON.  

**Resultado esperado:**  
- Status `200`.  
- Body deve conter os campos: `users` (array) e  `total`, `skip`, `limit`(numéricos) 
- Cada item de `users` deve conter os campos: `id`, `firstName`, `lastName`, `maidenName`, `age`, `gender`, `email`, `phone`, `username`, `password`, `birthDate`, `image`, `bloodGroup`, `height`, `weight`, `eyeColor`, `hair`, `ip`, `address`,  `macAddress`, `university`, `bank`, `company`, `ein`, `ssn`, `userAgent`, `crypto`, `role`.  
- Estrutura completa validada contra `schemas/users-list-schema.json`.
- **Teardown:** N/A.  
- **Observações:** Comparar `users.length` com o campo `limit`.

---

## API-USER-002 — Buscar usuário por ID válido
**Objetivo:** Validar consulta de usuário existente. 
**Pré-condição:**
- `baseUrl = https://dummyjson.com` configurado
- Usuário com `id==4` deve existir.
**Passos:**  
1. Executar `GET /users/4`. 
2. Observar a resposta JSON. 

**Resultado esperado:**  
- Status `200`.  
- Body é objeto único.  
- Body deve conter os campos: `id`, `firstName`, `lastName`, `maidenName`, `age`, `gender`, `email`, `phone`, `username`, `password`, `birthDate`, `image`, `bloodGroup`, `height`, `weight`, `eyeColor`, `hair`, `ip`, `address`,  `macAddress`, `university`, `bank`, `company`, `ein`, `ssn`, `userAgent`, `crypto`, `role`.   
- Estrutura completa validada contra `schemas/user-response-schema-full.json`
- **Teardown:** N/A.  
- **Observações:** Validar se `id == 4`.

---

## API-USER-003 — Criar novo usuário (válido)
**Objetivo:** Validar criação de usuário.  
**Pré-condição:** `baseUrl = https://dummyjson.com` configurado.  
**Dados de entrada:**  
```json
{
    "firstName": "João Víctor",
    "lastName": "Santos Campos",
    "maidenName": "",
    "age": 45,
    "gender": "male",
    "email": "james.davis@x.dummyjson.com",
    "phone": "+49 614-958-9364",
    "username": "jamesd",
    "password": "jamesdpass",
    "birthDate": "1979-5-4",
    "image": "https://dummyjson.com/icon/jamesd/128",
    "bloodGroup": "AB+",
    "height": 193.31,
    "weight": 62.1,
    "eyeColor": "Amber",
    "hair": {
        "color": "Blonde",
        "type": "Straight"
    },
    "ip": "101.118.131.66",
    "address": {
        "address": "238 Jefferson Street",
        "city": "Seattle",
        "state": "Pennsylvania",
        "stateCode": "PA",
        "postalCode": "68354",
        "coordinates": {
            "lat": 16.782513,
            "lng": -139.34723
        },
        "country": "United States"
    },
    "macAddress": "10:7d:df:1f:97:58",
    "university": "University of Southern California",
    "bank": {
        "cardExpire": "05/29",
        "cardNumber": "5005519846254763",
        "cardType": "Mastercard",
        "currency": "INR",
        "iban": "7N7ZH1PJ8Q4WU1K965HQQR27"
    },
    "company": {
        "department": "Support",
        "name": "Pagac and Sons",
        "title": "Research Analyst",
        "address": {
            "address": "1622 Lincoln Street",
            "city": "Fort Worth",
            "state": "Pennsylvania",
            "stateCode": "PA",
            "postalCode": "27768",
            "coordinates": {
                "lat": 54.91193,
                "lng": -79.498328
            },
            "country": "United States"
        }
    },
    "ein": "904-810",
    "ssn": "116-951-314",
    "userAgent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.99 Safari/537.36",
    "crypto": {
        "coin": "Bitcoin",
        "wallet": "0xb9fc2fe63b2a6c003f1c324c3bfa53259162181a",
        "network": "Ethereum (ERC20)"
    },
    "role": "admin"
}
```  
**Passos:**  
1. Executar `POST /users/add` com o body acima.
2. Observar a resposta JSON.

**Resultado esperado:**  
- Status `201`. 
- Body é objeto único. 
- Body contém `id` gerado.  
- Body de resposta deve retornar somente os campos: `id`, `firstName`, `lastName`, `maidenName`, `age`, `gender`, `email`, `phone`, `username`, `password`, `birthDate`, `image`, `bloodGroup`, `height`, `weight`, `eyeColor`, `hair`, `domain`,`ip`, `address`,  `macAddress`, `university`, `bank`, `company`, `ein`, `ssn`, `userAgent`.
- Campos adicionais enviados no body da requisição (ex.: `crypto` e `role`) não são refletidos no retorno, pois o comportamento da API é retornar apenas um subconjunto de atributos ao criar um produto.
- Cada campo deve ter o tipo correto conforme contrato
- Esse é o único body que retorna o campo `domain`.
- Estrutura válida contra `user-create-response-schema.json`.  
**Teardown:** N/A.   
**Observações:** A API DummyJSON não persiste dados e retorna apenas um subconjunto dos campos enviados no POST. Esse comportamento é esperado e foi considerado na definição do schema de validação.

---

## API-USER-004 — Atualizar usuário existente
**Objetivo:** Validar atualização de usuário. 
**Pré-condição:**
- `baseUrl = https://dummyjson.com` configurado
- Usuário com `id==4` deve existir.

**Dados de entrada:**  
```json
{
    "firstName": "João Víctor",
    "lastName": "Santos Campos",
    "maidenName": "",
    "age": 45,
    "gender": "male",
    "email": "james.davis@x.dummyjson.com",
    "phone": "+49 614-958-9364",
    "username": "jamesd",
    "password": "jamesdpass",
    "birthDate": "1979-5-4",
    "image": "https://dummyjson.com/icon/jamesd/128",
    "bloodGroup": "AB+",
    "height": 193.31,
    "weight": 62.1,
    "eyeColor": "Amber",
    "hair": {
        "color": "Blonde",
        "type": "Straight"
    },
    "ip": "101.118.131.66",
    "address": {
        "address": "238 Jefferson Street",
        "city": "Seattle",
        "state": "Pennsylvania",
        "stateCode": "PA",
        "postalCode": "68354",
        "coordinates": {
            "lat": 16.782513,
            "lng": -139.34723
        },
        "country": "United States"
    },
    "macAddress": "10:7d:df:1f:97:58",
    "university": "University of Southern California",
    "bank": {
        "cardExpire": "05/29",
        "cardNumber": "5005519846254763",
        "cardType": "Mastercard",
        "currency": "INR",
        "iban": "7N7ZH1PJ8Q4WU1K965HQQR27"
    },
    "company": {
        "department": "Support",
        "name": "Pagac and Sons",
        "title": "Research Analyst",
        "address": {
            "address": "1622 Lincoln Street",
            "city": "Fort Worth",
            "state": "Pennsylvania",
            "stateCode": "PA",
            "postalCode": "27768",
            "coordinates": {
                "lat": 54.91193,
                "lng": -79.498328
            },
            "country": "United States"
        }
    },
    "ein": "904-810",
    "ssn": "116-951-314",
    "userAgent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.99 Safari/537.36",
    "crypto": {
        "coin": "Bitcoin",
        "wallet": "0xb9fc2fe63b2a6c003f1c324c3bfa53259162181a",
        "network": "Ethereum (ERC20)"
    },
    "role": "admin"
}
```  
**Passos:**  
1. Executar `PUT /users/4` com o body acima.
2. Observar a resposta JSON.
**Resultado esperado:**  
- Status `200`. 
- Body de resposta deve retornar somente os campos: `id`, `firstName`, `lastName`, `maidenName`, `age`, `gender`, `email`, `phone`, `username`, `password`, `birthDate`, `image`, `bloodGroup`, `height`, `weight`, `eyeColor`, `hair`,`ip`, `address`,  `macAddress`, `university`, `bank`, `company`, `ein`, `ssn`, `userAgent`. 
- Campo `id` deve ser igual ao informado na rota (`4`).
- Valores retornados devem refletir os valores enviados no request para os campos atualizados (`id`, `firstName`, `lastName`, `maidenName`, `age`, `gender`, `email`, `phone`, `username`, `password`, `birthDate`, `image`, `bloodGroup`, `height`, `weight`, `eyeColor`, `hair`,`ip`, `address`,  `macAddress`, `university`, `bank`, `company`, `ein`, `ssn`, `userAgent`).
- Campos adicionais enviados no body da requisição (ex.: `crypto` e `role`) não são refletidos no retorno, pois o comportamento da API é retornar apenas um subconjunto de atributos ao atualizar um usuário.
- Cada campo deve ter o tipo correto conforme contrato.
- Estrutura válida contra `user-update-response-schema.json`.  
**Teardown:** N/A — A API DummyJSON não persiste alterações, portanto não há necessidade de restaurar estado após o teste.   
**Observações:** A API DummyJSON não persiste dados e retorna apenas um subconjunto dos campos enviados no PUT. Esse comportamento é esperado e foi considerado na definição do schema de validação.

---

## API-USER-005 — Remover usuário existente
**Objetivo:** Validar exclusão de usuário.  
**Pré-condição:** 
- `baseUrl = https://dummyjson.com` configurado
- Produto com `id==4` deve existir.

**Passos:**  
1. Executar `DELETE /users/4`.
2. Observar a resposta JSON.

**Resultado esperado:**  
- `DELETE` retorna status `200`.
- Body de resposta deve retornar os campos: `id`, `firstName`, `lastName`, `maidenName`, `age`, `gender`, `email`, `phone`, `username`, `password`, `birthDate`, `image`, `bloodGroup`, `height`, `weight`, `eyeColor`, `hair`,`ip`, `address`,  `macAddress`, `university`, `bank`, `company`, `ein`, `ssn`, `userAgent`, `crypto`, `role`.
- Além dos campos acima, o Body deve conter os campos específicos de exclusão:
  - `isDeleted` (booleano) → deve ser `true`.
  - `deletedOn` (string em formato `date-time`) → data/hora em que o usuário foi marcado como excluído.
- Estrutura completa validada contra `schemas/user-response-schema-deleted.json`  
**Teardown:** N/A (API DummyJSON não persiste exclusão).  
**Observações:** Apesar de retornar `isDeleted=true`, o produto continua acessível em chamadas futuras, pois a API DummyJSON é apenas simulada.

---

## API-USER-006 — Validação de contrato (Schema)
**Objetivo:** Garantir que as respostas das operações de usuários estejam em conformidade com os contratos definidos (schemas JSON).
**Pré-condição:** 
- Schemas JSON salvos em `/docs/casos-de-teste/api/schemas/`:
  - `users-list-schema.json` → contrato para listagem de usuários `(GET /users)`.
  - `user-response-schema-full.json` → contrato para consulta de usuário por ID válido `(GET /users/{id})`.
  - `user-create-response-schema.json` → contrato para criação de usuário (`POST /users/add`).
  - `user-update-response-schema.json` → contrato para atualização de usuário (PUT /users/{id}`).
  - `user-delete-response-schema.json` → contrato para exclusão de usuário `(DELETE /users/{id})`.

**Passos:**  
1. Executar `GET /users` e validar a resposta contra `users-list-schema.json`.
2. Executar `GET /users/4` e validar a resposta contra `users-response-schema-full.json`.
3. Executar `POST /users/add` com body válido e validar a resposta contra `user-create-response-schema.json`.
4. Executar `PUT /users/4` com body válido e validar a resposta contra `user-update-response-schema.json`.
5. Executar `DELETE /users/4` e validar a resposta contra `user-delete-response-schema.json`.  

**Resultado esperado:**  
- Todas as respostas devem estar em conformidade com seus respectivos schemas.
- Se ocorrer divergência, deve ser identificado:
  - Qual endpoint apresentou falha.
  - Qual campo divergiu (ausente, tipo incorreto, formato inválido, etc.).

**Teardown:** N/A (a API DummyJSON não persiste operações, logo não há impacto nos dados).
**Observações:** Esse teste garante a conformidade estrutural de todos os endpoints principais de usuário, cobrindo o ciclo CRUD completo.

---

# Observações gerais
- Todos os testes devem salvar evidências (respostas JSON) em `/evidence/api/users/`.   
