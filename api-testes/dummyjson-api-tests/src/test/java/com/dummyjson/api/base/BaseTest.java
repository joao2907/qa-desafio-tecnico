package com.dummyjson.api.base;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://dummyjson.com";
    }
}
