package pojoTest;

import base.BaseTest;
import constants.ColumnsEndPoints;
import data.DataTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Column;

import static Regex.Regex.pattern2;

public class pojoCreateColumn  extends BaseTest {


@Test (groups = "column",dependsOnGroups = "board")

    public void Create_New_Column(){

    Column column = Column.builder()
            .title("ToDo")
            .sort_order(4)
            .type(1)
            .build();

    String endpoint = String.format(ColumnsEndPoints.CREATE_COLUMN, DataTest.boardId);

    Response response = request
            .body(column)
            .when()
            .post(endpoint)
            .then()
            .log()
            .all()
            .extract().response();

    Assert.assertEquals(response.statusCode(),200);
    Assert.assertTrue(response.jsonPath().getString("type").matches(pattern2));

}



}
