package pojoTest;

import base.BaseTest;
import constants.BoardEndPoints;
import data.DataTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Board;

import static Regex.Regex.pattern1;
import static Regex.Regex.pattern2;

public class pojoCreateBoard  extends BaseTest {

    @Test (groups = "board", dependsOnGroups = "space")

    public void createNewBoard() {

        Board board = Board.builder()
                .title("Privet")
                .description("tasks")
                .build();

        String endpoint = String.format(BoardEndPoints.CREATE_BOARD, DataTest.spaceId);

        Response response =
                request
                        .body(board)
                        .when()
                        .post(endpoint)
                        .then()
                        .log()
                        .all()
                        .extract().response();
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.jsonPath().getString("title").matches(pattern2));
        Assert.assertTrue(response.jsonPath().getString("top").matches(pattern1));

        DataTest.boardId = response.jsonPath().getInt("id");



    }
}


