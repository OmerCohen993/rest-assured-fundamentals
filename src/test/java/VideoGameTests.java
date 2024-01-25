import config.VideoGameConfig;
import config.VideoGameEndpoints;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class VideoGameTests extends VideoGameConfig {

    String gameBodyJson = "{\n" +
            "  \"category\": \"Platform\",\n" +
            "  \"name\": \"Mario\",\n" +
            "  \"rating\": \"Mature\",\n" +
            "  \"releaseDate\": \"2022-05-04\",\n" +
            "  \"reviewScore\": 89\n" +
            "}";

    @Test
    public void getAllGames() {
        given()
                .when()
                .get(VideoGameEndpoints.ALL_VIDEO_GAMES)
                .then();

    }

    @Test
    public void createNewGameByJson() {

        given()
                .body(this.gameBodyJson)
                .when()
                .post(VideoGameEndpoints.ALL_VIDEO_GAMES)
                .then();
    }

    @Test
    public void createNewGameByXml() {
        String gameBodyXml = "<VideoGameRequest>\n" +
                "\t<category>Platform</category>\n" +
                "\t<name>Mario</name>\n" +
                "\t<rating>Mature</rating>\n" +
                "\t<releaseDate>2012-05-04</releaseDate>\n" +
                "\t<reviewScore>85</reviewScore>\n" +
                "</VideoGameRequest>";

        given()
                .body(gameBodyXml)
                .contentType("application/xml")
                .accept("application/xml")
                .when()
                .post(VideoGameEndpoints.ALL_VIDEO_GAMES)
                .then();
    }

    @Test
    public void updateGame() {
        given()
                .body(this.gameBodyJson)
                .pathParam("videoGameId", 5)
                .when()
                .put(VideoGameEndpoints.SINGLE_VIDEO_GAMES)
                .then();
    }

    @Test
    public void deleteGame() {
        given()
                .accept("text/plain")
                .pathParam("videoGameId", 5)
                .when()
                .delete(VideoGameEndpoints.SINGLE_VIDEO_GAMES)
                .then();
    }

    @Test
    public void getSingleGame() {
        given()
                .pathParam("videoGameId", 5)
                .when()
                .get(VideoGameEndpoints.SINGLE_VIDEO_GAMES)
                .then();
    }

}
