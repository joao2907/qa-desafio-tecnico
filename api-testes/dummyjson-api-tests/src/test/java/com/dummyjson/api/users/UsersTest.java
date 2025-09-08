package com.dummyjson.api.users;

import com.dummyjson.api.base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class UsersTest extends BaseTest {

    @Test
    public void testListAllUsers() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("users", notNullValue())
                .body("total", greaterThan(0))
                .body("skip", isA(Integer.class))
                .body("limit", isA(Integer.class))
                .body(matchesJsonSchemaInClasspath("schemas/users-list-schema.json"));
    }

    @Test
    public void testGetUserById() {
        RestAssured
                .given()
                .pathParam("id", 4)
                .when()
                .get("/users/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(4))
                .body("firstName", notNullValue())
                .body("lastName", notNullValue())
                .body("email", notNullValue())
                .body(matchesJsonSchemaInClasspath("schemas/user-response-schema-full.json"));
    }

    @Test
    public void testCreateUser() {
        String requestBody = """
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
          "hair": { "color": "Blonde", "type": "Straight" },
          "ip": "101.118.131.66",
          "address": {
            "address": "238 Jefferson Street",
            "city": "Seattle",
            "state": "Pennsylvania",
            "stateCode": "PA",
            "postalCode": "68354",
            "coordinates": { "lat": 16.782513, "lng": -139.34723 },
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
              "coordinates": { "lat": 54.91193, "lng": -79.498328 },
              "country": "United States"
            }
          },
          "ein": "904-810",
          "ssn": "116-951-314",
          "userAgent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7)",
          "crypto": {
            "coin": "Bitcoin",
            "wallet": "0xb9fc2fe63b2a6c003f1c324c3bfa53259162181a",
            "network": "Ethereum (ERC20)"
          },
          "role": "admin"
        }
        """;

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/users/add")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("firstName", equalTo("João Víctor"))
                .body("lastName", equalTo("Santos Campos"))
                .body(matchesJsonSchemaInClasspath("schemas/user-create-response-schema.json"));
    }

    @Test
    public void testUpdateUser() {
        String requestBody = """
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
        """;

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .pathParam("id", 4)
                .body(requestBody)
                .when()
                .put("/users/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(4))
                .body("firstName", equalTo("João Víctor"))
                .body("lastName", equalTo("Santos Campos"))
                .body(matchesJsonSchemaInClasspath("schemas/user-update-response-schema.json"));
    }

    @Test
    public void testDeleteUser() {
        RestAssured
                .given()
                .pathParam("id", 4)
                .when()
                .delete("/users/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(4))
                .body("isDeleted", equalTo(true))
                .body("deletedOn", notNullValue())
                .body(matchesJsonSchemaInClasspath("schemas/user-response-schema-deleted.json"));
    }
}
