package io.quarkus;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

@QuarkusTest
@Disabled
public class GreetingTest
{
    // NOTE: RestAssured is aware of the application.properties quarkus.http.root-path switch

    @Test
    public void testJaxrs() {
        RestAssured.when().get("/jaxrs").then()
                .statusCode(200)
                .body(not((emptyOrNullString())));
    }

    @Test
    public void testFunqy() {
        RestAssured.when().get("/funqy?times=10").then()
                .statusCode(200)
                .body(not((emptyOrNullString())));
    }
}
