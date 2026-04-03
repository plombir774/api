package tests.e2e;

import constants.SpaceEndPoints;
import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;



public class CreateAndUpdateAndGetAndDelete extends BaseTest {

    @Test
    public void create_AND_Update_And_Get_Delete(){

        String createSpace = """
                {
                "title": "OpenSpace",
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
        Assert.assertEquals(response.jsonPath().getString("title"),"OpenSpace");

        int spaceId = response.jsonPath().getInt("id");

        String spaceBody = """
                {
                "title": "test",
                "external_id": 2
                }""";

        String endpoint = String.format(
                SpaceEndPoints.UPDATE_SPACE,
                spaceId);

        Response response1 = request
                .body(spaceBody)
                .when()
                .patch(endpoint)
                .then()
                .log()
                .all()
                .extract().response();

        Assert.assertEquals(response1.statusCode(),200);
        Assert.assertEquals(response1.jsonPath().getString("title"),"test");
        Assert.assertEquals(response1.jsonPath().getInt("external_id"),2);

        String endpointGet = String.format(
                SpaceEndPoints.GET_SPACE,
                spaceId);

        Response response2 = request
                .when()
                .get(endpointGet)
                .then()
                .log()
                .all()
                .extract().response();

        Assert.assertEquals(response2.statusCode(),200);
        Assert.assertEquals(response2.jsonPath().getString("title"),"test");
        Assert.assertEquals(response2.jsonPath().getInt("external_id"),2);

        String deleteEndpoint = String.format(
                SpaceEndPoints.DELETE_SPACE,
                spaceId);

        Response response3 = request
                .when()
                .delete(deleteEndpoint)
                .then()
                .log()
                .all()
                .extract().response();

        Assert.assertEquals(response3.statusCode(),200);
    }
}