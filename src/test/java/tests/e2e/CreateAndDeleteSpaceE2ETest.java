package tests.e2e;

import constants.SpaceEndPoints;
import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAndDeleteSpaceE2ETest extends BaseTest {

    @Test
    public void create_AND_DELETE_SPACE(){

        String createSpace = """
                {
                "title": "03.04.2026",
                "external_id": 1
                }""";

        Response response = request
                .body(createSpace)
                .when()
                .post(SpaceEndPoints.CREATE)
                .then()
                .log()
                .all()
                .extract().response();

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.jsonPath().getInt("external_id"),1);
        Assert.assertEquals(response.jsonPath().getString("title"),"03.04.2026");

        int spaceId = response.jsonPath().getInt("id");

        String deletePoint = String.format(
                SpaceEndPoints.DELETE_SPACE,
                spaceId);

        Response response1 = request
                .when()
                .delete(deletePoint)
                .then()
                .log()
                .all()
                .extract().response();

        Assert.assertEquals(response1.statusCode(),200);

        String get_endpoint = String.format(
                SpaceEndPoints.GET_SPACE,
                spaceId);

        Response response2 = request
                .when()
                .get(get_endpoint)
                .then()
                .log()
                .all()
                .extract().response();

        Assert.assertEquals(response2.statusCode(),404);
    }
}