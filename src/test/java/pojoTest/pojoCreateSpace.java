package pojoTest;

import base.BaseTest;
import constants.SpaceEndPoints;
import data.DataTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Space;

import static Regex.Regex.pattern;
import static Regex.Regex.pattern1;

public class pojoCreateSpace  extends BaseTest {



    @Test ( dataProvider = "spaceData" ,dataProviderClass = DataTest.class, groups = "space")

    public void createNewSpace(String title,Integer external_Id) {
        Space space = Space.builder()
                .title(title)
                .external_id(external_Id)
                .build();

        Response response =
                request
                        .body(space)
                        .when()
                        .post(SpaceEndPoints.CREATE)
                        .then()
                        .log()
                        .all()
                        .extract().response();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.jsonPath().getString("created").matches(pattern));
        Assert.assertTrue(response.jsonPath().getString("id").matches(pattern1));

        DataTest.spaceId = response.jsonPath().getInt("id");

    }
}


