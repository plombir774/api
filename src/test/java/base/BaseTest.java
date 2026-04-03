package base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;


import java.util.Objects;

public class BaseTest {
    protected RequestSpecification request;


    @BeforeMethod

    public void setUp(){
        RestAssured.baseURI = "https://plombir774.kaiten.ru/api/latest";
        String token = Objects.requireNonNull(
                System.getenv("API_TOKEN"),
                "API_TOKEN не задан");

        request = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer " + token);
    }


    @AfterTest
    public void teardown(){

    }
}
