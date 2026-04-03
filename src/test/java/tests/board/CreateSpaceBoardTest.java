package tests.board;

import constants.BoardEndPoints;
import base.BaseTest;
import data.DataTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateSpaceBoardTest extends BaseTest {

   public  String body = """
           {
           
             "title": "test",
             "columns": [
             
             {"title":"To Do",
             "type":1,
             "description":" все текущие задачи на сегодня"}
             ]
             }""";


    @Test(groups = "board",dependsOnGroups = "space")

    public void createNewBoard(){
        String endpoint = String.format(BoardEndPoints.CREATE_BOARD, DataTest.spaceId);

        Response response =
                request
                        .body(body)
                        .when()
                        .post(endpoint)
                        .then()
                        .log()
                        .all()
                        .extract().response();
        Assert.assertEquals(response.statusCode(),200);

        DataTest.boardId = response.jsonPath().getInt("id");

    }
}
