package base;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.BeforeMethod;


import java.util.Objects;

public class BaseTest {

    protected RequestSpecification request;


    @BeforeMethod

    public void setUp() {
        System.out.println("setUp started");
        RestAssured.baseURI = "https://plombir774.kaiten.ru/api/latest";
        String token = Objects.requireNonNull(
                System.getenv("API_TOKEN"),
                "API_TOKEN не задан");


        request = RestAssured
                .given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token);
    }
}


