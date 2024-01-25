import config.VideoGameConfig;
import config.VideoGameEndpoints;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class VideoGameTests extends VideoGameConfig {

    @Test
    public void getAllGames(){
        given()
        .when()
                .get(VideoGameEndpoints.ALL_VIDEO_GAMES)
        .then();

    }

    @Test
    public void createNewGameByJson(){
        String gameBodyJson = "{\n" +
                "  \"category\": \"Platform\",\n" +
                "  \"name\": \"Mario\",\n" +
                "  \"rating\": \"Mature\",\n" +
                "  \"releaseDate\": \"2022-05-04\",\n" +
                "  \"reviewScore\": 89\n" +
                "}";

        given()
                    .body(gameBodyJson)
                .when()
                    .post(VideoGameEndpoints.ALL_VIDEO_GAMES)
                .then();
    }
}
