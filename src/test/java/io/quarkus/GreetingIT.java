package io.quarkus;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.nio.charset.StandardCharsets;
import org.apache.http.params.CoreConnectionPNames;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GreetingIT {

    RestAssuredConfig config;

    @BeforeEach
    void setUp() {
        config = RestAssured.config()
            .httpClient(HttpClientConfig.httpClientConfig()
                .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000)
                .setParam(CoreConnectionPNames.SO_TIMEOUT, 5000));
    }

    @Test
    public void testGetStringLessThen8KB_text() {
        Response response = RestAssured.given().config(config)
            .queryParam("times", "78")
            .accept(ContentType.TEXT)
            .get("http://localhost:7071/api/hello/getString");

        assertResponse(response);
    }

    @Test
    public void testGetStringGreaterThen8KB_text() {
        Response response = RestAssured.given().config(config)
            .queryParam("times", "300")
            .accept(ContentType.TEXT)
            .get("http://localhost:7071/api/hello/getString");

        assertResponse(response);
    }

    @Test
    public void testGetStringLessThen8KB_json() {
        Response response = RestAssured.given().config(config)
            .queryParam("times", "78")
            .accept(ContentType.JSON)
            .get("http://localhost:7071/api/hello/getString");

        assertResponse(response);
    }
    @Test
    public void testGetStringGreaterThen8KB_json() {
        Response response = RestAssured.given().config(config)
            .queryParam("times", "300")
            .accept(ContentType.JSON)
            .get("http://localhost:7071/api/hello/getString");

        assertResponse(response);
    }



    private static void assertResponse(Response response) {
        response.then()
            .statusCode(200);

        String bodyString = response.body().asPrettyString();
        System.out.println("response body size=" + bodyString.getBytes(StandardCharsets.UTF_8).length);
        bodyString.startsWith(GreetingResource.BASIC_STRING);
    }
}
