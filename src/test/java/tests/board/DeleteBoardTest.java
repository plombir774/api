package tests.board;

import constants.BoardEndPoints;
import base.BaseTest;
import data.DataTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteBoardTest extends BaseTest {

@Test( groups = "delete",dependsOnGroups = "board")

    public void DeleteBoard(){
    String endpoint = String.format(
            BoardEndPoints.DELETE_BOARD, DataTest.spaceId, DataTest.boardId);

    String body = """
                {
                  "force": true
                }
                """;


    Response response =
            request
                    .body(body)
                    .when()
                    .delete(endpoint)
                    .then()
                    .log()
                    .all()
                    .extract().response();

    Assert.assertEquals(response.statusCode(),200);



}
}
