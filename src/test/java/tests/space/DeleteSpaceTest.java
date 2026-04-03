package tests.space;

import constants.SpaceEndPoints;
import base.BaseTest;
import data.DataTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;



public class DeleteSpaceTest extends BaseTest {

    @Test(dependsOnGroups = "delete")

    public void DeleteSpaces(){
        String endpoint = String.format(
                SpaceEndPoints.DELETE_SPACE, DataTest.spaceId);

        Response response = request
                .when()
                .delete(endpoint)
                .then()
                .log()
                .all()
                .extract().response();

        Assert.assertEquals(response.statusCode(),200);
    }
}
