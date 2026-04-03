package tests.e2e;

import constants.BoardEndPoints;
import constants.SpaceEndPoints;
import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SpaceBoardWorkflowTest extends BaseTest {

    @Test
    public void fullFlow() {

        String spaceBody = """
                {
                  "title": "E2E Space"
                }
                """;

        Response spaceResponse = request
                .body(spaceBody)
                .when()
                .post(SpaceEndPoints.CREATE)
                .then()
                .log().all()
                .extract().response();

        Assert.assertEquals(spaceResponse.statusCode(), 200);

        int spaceId = spaceResponse.jsonPath().getInt("id");

        String boardBody = """
                {
                  "title": "E2E Board",
                  "columns": [
                    {
                      "title": "To Do",
                      "type": 1,
                      "description": "tasks"
                    }
                  ]
                }
                """;

        String createBoardEndpoint = String.format(BoardEndPoints.CREATE_BOARD, spaceId);

        Response boardResponse = request
                .body(boardBody)
                .when()
                .post(createBoardEndpoint)
                .then()
                .log().all()
                .extract().response();

        Assert.assertEquals(boardResponse.statusCode(), 200);

        int boardId = boardResponse.jsonPath().getInt("id");

        String deleteBoardEndpoint = String.format(BoardEndPoints.DELETE_BOARD, spaceId, boardId);

        Response deleteBoardResponse = request
                .body("""
                        {
                          "force": true
                        }
                        """)
                .when()
                .delete(deleteBoardEndpoint)
                .then()
                .log().all()
                .extract().response();

        Assert.assertEquals(deleteBoardResponse.statusCode(), 200);

        String deleteSpaceEndpoint = String.format(SpaceEndPoints.DELETE_SPACE, spaceId);

        Response deleteSpaceResponse = request
                .when()
                .delete(deleteSpaceEndpoint)
                .then()
                .log().all()
                .extract().response();

        Assert.assertEquals(deleteSpaceResponse.statusCode(), 200);
    }
}