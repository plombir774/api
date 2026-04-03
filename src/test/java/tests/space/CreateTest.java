package tests.space;

import constants.SpaceEndPoints;
import base.BaseTest;
import data.DataTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;



public class CreateTest extends BaseTest {

    public String body = """
 {
 "title": "OpenSpace"
}
            """;

@Test(groups  = "space")
    public void createNewSpace(){

        Response response =
                request
                        .body(body)
                        .when()
                        .post(SpaceEndPoints.CREATE)
                        .then()
                        .log()
                        .all()
                        .extract().response();

    Assert.assertEquals(response.statusCode(),200);
    Assert.assertEquals(response.jsonPath().getString("title"),"OpenSpace");
    Assert.assertFalse(response.jsonPath().getBoolean("archived"));

    DataTest.spaceId = response.jsonPath().getInt("id");

    }
}
