package com.dummyjson.api.products;

import com.dummyjson.api.base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class ProductsTest extends BaseTest {

    @Test
    public void testListAllProducts() {
        RestAssured
                .given()
                .contentType(ContentType.JSON) // define que queremos enviar/receber JSON
                .when()
                .get("/products") // faz a chamada GET para o endpoint.
                .then()
                .statusCode(200) // garante que o status HTTP seja 200 (sucesso).
                .body("products", notNullValue()) // garante que a chave products exista e não seja nula.
                .body("total", greaterThan(0)) // o campo total deve ser maior que 0.
                .body("skip", isA(Integer.class)) //  valida o tipo.
                .body("limit", isA(Integer.class))//  valida o tipo.
                .body(matchesJsonSchemaInClasspath("schemas/products-list-schema.json"));// valida se a resposta segue o contrato JSON definido no schema.
    }

    @Test
    public void testGetProductById() {
        RestAssured
                .given()
                .pathParam("id", 4)
                .when()
                .get("/products/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(4)) // Valida que no JSON de resposta, o campo id é exatamente 4.
                .body("title", notNullValue()) // Valida que o campo title existe e não é nulo
                .body("price", greaterThan(0.0f)) // Valida que o campo price é maior que 0.0.
                .body(matchesJsonSchemaInClasspath("schemas/product-response-schema-full.json"));
    }

    @Test
    public void testCreateFullProduct() {
        String requestBody = """
        {
          "title": "Batom Vermelho",
          "description": "The Red Lipstick is a classic and bold choice...",
          "category": "beauty",
          "price": 12.99,
          "discountPercentage": 12.16,
          "rating": 4.36,
          "stock": 91,
          "tags": ["beauty", "lipstick"],
          "brand": "Chic Cosmetics",
          "sku": "BEA-CHI-LIP-004",
          "weight": 1,
          "dimensions": { "width": 18.11, "height": 28.38, "depth": 22.17 },
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
        """;

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/products/add")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("title", equalTo("Batom Vermelho"))
                .body(matchesJsonSchemaInClasspath("schemas/product-response-schema-min.json"));
    }

    @Test
    public void testUpdateProduct() {
        String requestBody = """
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
        """;

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .pathParam("id", 4)
                .body(requestBody)
                .when()
                .put("/products/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(4))
                .body("title", equalTo("Batom Vermelho"))
                .body(matchesJsonSchemaInClasspath("schemas/product-response-schema-min.json"));
    }

    @Test
    public void testDeleteProduct() {
        RestAssured
                .given()
                .pathParam("id", 4)
                .when()
                .delete("/products/{id}")
                .then()
                .statusCode(200)
                .body("isDeleted", equalTo(true))
                .body("deletedOn", notNullValue())
                .body(matchesJsonSchemaInClasspath("schemas/product-response-schema-deleted.json"));
    }

}