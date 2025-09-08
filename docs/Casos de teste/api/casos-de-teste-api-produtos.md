# Casos de Teste — API Produtos (DummyJSON)

## API-PROD-001 — Listar todos os produtos
**Objetivo:** Validar a listagem de produtos.  
**Pré-condição:** `baseUrl = https://dummyjson.com` configurado.

**Passos:**  
1. Executar `GET /products`.  
2. Observar a resposta JSON.  

**Resultado esperado:**  
- Status `200`.  
- Body deve conter os campos: `products` (array) e  `total`, `skip`, `limit`(inteiro) 
- Cada item de `products` deve conter obrigatoriamente os campos: `id`, `title`, `description`, `category`, `price`, `discountPercentage`, `rating`, `stock`, `tags`, `brand`, `sku`, `weight`, `dimensions`, `warrantyInformation`, `shippingInformation`, `availabilityStatus`, `reviews`, `returnPolicy`, `minimumOrderQuantity`, `meta`, `images`, `thumbnail`. 
- Campos opcionais (podem ou não estar presentes dependendo do produto): `brand` 
- Estrutura completa validada contra `schemas/products-list-schema.json`.
- **Teardown:** N/A.  
- **Observações:** 
  - Comparar `products.length` com o campo `limit`.
  - A ausência de campos opcionais (como `brand`) não caracteriza falha.
  - O contrato da API deve garantir apenas a presença dos campos obrigatórios
  - A validação do schema deve considerar campos opcionais como não obrigatórios.
---

## API-PROD-002 — Buscar produto por ID válido
**Objetivo:** Validar consulta de produto existente.  
**Pré-condição:**
- `baseUrl = https://dummyjson.com` configurado
- Produto com `id==4` deve existir.
**Passos:**  
1. Executar `GET /products/4`. 
2. Observar a resposta JSON. 

**Resultado esperado:**  
- Status `200`.  
- Body é objeto único.  
- Body deve conter os campos: `id`, `title`, `description`, `category`, `price`, `discountPercentage`, `rating`, `stock`, `tags`, `sku`, `weight`, `dimensions`, `warrantyInformation`, `shippingInformation`, `availabilityStatus`, `reviews`, `returnPolicy`, `minimumOrderQuantity`, `meta`, `images`, `thumbnail`.
- Campos opcionais (podem ou não estar presentes dependendo do produto): `brand` 
- Estrutura completa validada contra `schemas/product-response-schema-full.json`
- **Teardown:** N/A.  
- **Observações:** 
  - Validar se `id == 4`.
  - A ausência de campos opcionais (como `brand`) não caracteriza falha.
  - O contrato da API deve garantir apenas a presença dos campos obrigatórios
  - A validação do schema deve considerar campos opcionais como não obrigatórios.
---

## API-PROD-003 — Criar novo produto (válido)
**Objetivo:** Validar criação de produto.  
**Pré-condição:** `baseUrl = https://dummyjson.com` configurado.  
**Dados de entrada:**  
```json
{
    "title": "Batom Vermelho",
    "description": "The Red Lipstick is a classic and bold choice for adding a pop of color to your lips. With a creamy and pigmented formula, it provides a vibrant and long-lasting finish.",
    "category": "beauty",
    "price": 12.99,
    "discountPercentage": 12.16,
    "rating": 4.36,
    "stock": 91,
    "tags": [
        "beauty",
        "lipstick"
    ],
    "brand": "Chic Cosmetics",
    "sku": "BEA-CHI-LIP-004",
    "weight": 1,
    "dimensions": {
        "width": 18.11,
        "height": 28.38,
        "depth": 22.17
    },
    "warrantyInformation": "3 year warranty",
    "shippingInformation": "Ships in 1 week",
    "availabilityStatus": "In Stock",
    "reviews": [
        {
            "rating": 4,
            "comment": "Great product!",
            "date": "2025-04-30T09:41:02.053Z",
            "reviewerName": "Liam Garcia",
            "reviewerEmail": "liam.garcia@x.dummyjson.com"
        },
        {
            "rating": 5,
            "comment": "Great product!",
            "date": "2025-04-30T09:41:02.053Z",
            "reviewerName": "Ruby Andrews",
            "reviewerEmail": "ruby.andrews@x.dummyjson.com"
        },
        {
            "rating": 5,
            "comment": "Would buy again!",
            "date": "2025-04-30T09:41:02.053Z",
            "reviewerName": "Clara Berry",
            "reviewerEmail": "clara.berry@x.dummyjson.com"
        }
    ],
    "returnPolicy": "7 days return policy",
    "minimumOrderQuantity": 40,
    "meta": {
        "createdAt": "2025-04-30T09:41:02.053Z",
        "updatedAt": "2025-04-30T09:41:02.053Z",
        "barcode": "9467746727219",
        "qrCode": "https://cdn.dummyjson.com/public/qr-code.png"
    },
    "images": [
        "https://cdn.dummyjson.com/product-images/beauty/red-lipstick/1.webp"
    ],
    "thumbnail": "https://cdn.dummyjson.com/product-images/beauty/red-lipstick/thumbnail.webp"
}
```  
**Passos:**  
1. Executar `POST /products/add` com o body acima.

**Resultado esperado:**  
- Status `201`. 
- Body é objeto único. 
- Body contém `id` gerado.  
- Body de resposta deve retornar somente os campos (`id`,`title`, `price`, `discountPercentage`, `stock`, `rating`, `images`, `thumbnail`, `description`, `brand`, `category`). 
- Campos adicionais enviados no body da requisição (ex.: `dimensions`, `reviews`, `meta`, etc.) não são refletidos no retorno, pois o comportamento da API é retornar apenas um subconjunto de atributos ao criar um produto.
- Cada campo deve ter o tipo correto conforme contrato
- Estrutura válida contra `product-response-schema-min.json`.  
**Teardown:** N/A.   
**Observações:** A API DummyJSON não persiste dados e retorna apenas um subconjunto dos campos enviados no POST. Esse comportamento é esperado e foi considerado na definição do schema de validação.

---

## API-PROD-004 — Atualizar produto existente
**Objetivo:** Validar atualização de produto.  
**Pré-condição:**
- `baseUrl = https://dummyjson.com` configurado
- Produto com `id==4` deve existir.

**Dados de entrada:**  
```json
{
    "title": "Batom Vermelho",
    "description": "The Red Lipstick is a classic and bold choice for adding a pop of color to your lips. With a creamy and pigmented formula, it provides a vibrant and long-lasting finish.",
    "category": "beauty",
    "price": 12.99,
    "discountPercentage": 12.16,
    "rating": 4.36,
    "stock": 91,
    "tags": [
        "beauty",
        "lipstick"
    ],
    "brand": "Chic Cosmetics",
    "sku": "BEA-CHI-LIP-004",
    "weight": 1,
    "dimensions": {
        "width": 18.11,
        "height": 28.38,
        "depth": 22.17
    },
    "warrantyInformation": "3 year warranty",
    "shippingInformation": "Ships in 1 week",
    "availabilityStatus": "In Stock",
    "reviews": [
        {
            "rating": 4,
            "comment": "Great product!",
            "date": "2025-04-30T09:41:02.053Z",
            "reviewerName": "Liam Garcia",
            "reviewerEmail": "liam.garcia@x.dummyjson.com"
        },
        {
            "rating": 5,
            "comment": "Great product!",
            "date": "2025-04-30T09:41:02.053Z",
            "reviewerName": "Ruby Andrews",
            "reviewerEmail": "ruby.andrews@x.dummyjson.com"
        },
        {
            "rating": 5,
            "comment": "Would buy again!",
            "date": "2025-04-30T09:41:02.053Z",
            "reviewerName": "Clara Berry",
            "reviewerEmail": "clara.berry@x.dummyjson.com"
        }
    ],
    "returnPolicy": "7 days return policy",
    "minimumOrderQuantity": 40,
    "meta": {
        "createdAt": "2025-04-30T09:41:02.053Z",
        "updatedAt": "2025-04-30T09:41:02.053Z",
        "barcode": "9467746727219",
        "qrCode": "https://cdn.dummyjson.com/public/qr-code.png"
    },
    "images": [
        "https://cdn.dummyjson.com/product-images/beauty/red-lipstick/1.webp"
    ],
    "thumbnail": "https://cdn.dummyjson.com/product-images/beauty/red-lipstick/thumbnail.webp"
}
```  
**Passos:**  
1. Executar `PUT /products/4` com o body acima.  
**Resultado esperado:**  
- Status `200`. 
- Body de resposta deve retornar apenas os campos (`id`,`title`, `price`, `discountPercentage`, `stock`, `rating`, `images`, `thumbnail`, `description`, `brand`, `category`). 
- Campos opcionais (podem ou não estar presentes dependendo do produto): `brand`
- Campo `id` deve ser igual ao informado na rota (`4`).
- Valores retornados devem refletir os valores enviados no request para os campos atualizados (`title`, `price`, `discountPercentage`, `stock`, `rating`, `images`, `thumbnail`, `description`, `brand`, `category`).
- Campos adicionais enviados no body da requisição (ex.: `dimensions`, `reviews`, `meta`, etc.) não são refletidos no retorno, pois o comportamento da API é retornar apenas um subconjunto de atributos ao atualizar um produto.
- Cada campo deve ter o tipo correto conforme contrato.
- Estrutura válida contra `product-response-schema-min.json`.  
**Teardown:** N/A — A API DummyJSON não persiste alterações, portanto não há necessidade de restaurar estado após o teste.   
**Observações:** A API DummyJSON não persiste dados e retorna apenas um subconjunto dos campos enviados no PUT. Esse comportamento é esperado e foi considerado na definição do schema de validação.

---

## API-PROD-005 — Remover produto existente
**Objetivo:** Validar exclusão de produto.  
**Pré-condição:** 
- `baseUrl = https://dummyjson.com` configurado
- Produto com `id==4` deve existir.

**Passos:**  
1. Executar `DELETE /products/4`.
2. Observar a resposta JSON.

**Resultado esperado:**  
- `DELETE` retorna status `200`.
- Body deve conter os mesmos campos de um produto completo (`id`,`title`, `description`, `category`, `price`, `discountPercentage`, `rating`, `stock`, `tags`, `brand`, `sku`, `weight`, `dimensions`, `warrantyInformation`, `shippingInformation`, `availabilityStatus`, `reviews`, `returnPolicy`, `minimumOrderQuantity`, `meta`, `images`, `thumbnail`, `isDeleted`, `deletedOn`) 
- Além dos campos acima, o Body deve conter os campos específicos de exclusão:
  - `isDeleted` (booleano) → deve ser `true`.
  - `deletedOn` (string em formato `date-time`) → data/hora em que o produto foi marcado como excluído.
- Estrutura completa validada contra `schemas/product-response-schema-deleted.json`  
**Teardown:** N/A (API DummyJSON não persiste exclusão).  
**Observações:** Apesar de retornar `isDeleted=true`, o produto continua acessível em chamadas futuras, pois a API DummyJSON é apenas simulada.

---

## API-PROD-006 — Validação de contrato (Schema)
**Objetivo:** Garantir que as respostas das operações de produto estejam em conformidade com os contratos definidos (schemas JSON).  
**Pré-condição:** 
- Schemas JSON salvos em `/docs/casos-de-teste/api/schemas/`:
  - `products-list-schema.json` → contrato para listagem de produtos `(GET /products)`.
  - `product-response-schema-full.json` → contrato para consulta de produto por ID válido `(GET /products/{id})`.
  - `product-response-schema-min.json` → contrato para criação/atualização de produto (`POST /products/add` e `PUT /products/{id}`).
  - `product-response-schema-deleted.json` → contrato para exclusão de produto `(DELETE /products/{id})`.

**Passos:**  
1. Executar `GET /products` e validar a resposta contra `products-list-schema.json`.
2. Executar `GET /products/4` e validar a resposta contra `product-response-schema-full.json`.
3. Executar `POST /products/add` com body válido e validar a resposta contra `product-response-schema-min.json`.
4. Executar `PUT /products/4` com body válido e validar a resposta contra `product-response-schema-min.json`.
5. Executar `DELETE /products/4` e validar a resposta contra `product-response-schema-deleted.json`.  

**Resultado esperado:**  
- Todas as respostas devem ser válidas segundo seus respectivos schemas.
- Se ocorrer divergência, deve ser identificado:
  - Qual endpoint apresentou falha.
  - Qual campo divergiu (ausente, tipo incorreto, formato inválido, etc.).

**Teardown:** N/A (a API DummyJSON não persiste operações)  
**Observações:** Esse teste garante a conformidade estrutural de todos os endpoints principais de produto, cobrindo todo o ciclo CRUD.

---

# Observações gerais
- Todos os testes devem salvar evidências (respostas JSON) em `/evidence/api/products/`.  
- Casos negativos são tão importantes quanto os positivos: demonstram robustez da API.  