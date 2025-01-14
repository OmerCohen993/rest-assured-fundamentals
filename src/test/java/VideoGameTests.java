import config.VideoGameConfig;
import config.VideoGameEndpoints;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import objects.VideoGame;
import org.junit.Test;


import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.lessThan;

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

    @Test
    public void testVideoGameSerializationByJson() {
        VideoGame videoGame = new VideoGame("Shooter", "OmerCOD", "Mature", "2023-12-3", 100);

        given()
                .body(videoGame) //converting class to json
                .when()
                .post(VideoGameEndpoints.ALL_VIDEO_GAMES)
                .then();
    }

    @Test
    public void convertJsonToPojo() {
        Response response =
                given()
                        .pathParam("videoGameId", 5)
                        .when()
                        .get(VideoGameEndpoints.SINGLE_VIDEO_GAMES);

        VideoGame videoGame = response.getBody().as(VideoGame.class);
        System.out.println(videoGame.toString());
    }

    @Test
    public void testVideoGameSchemaXML() {
        given()
                .pathParam("videoGameId", 5)
                .accept("application/xml")
                .when()
                .get(VideoGameEndpoints.SINGLE_VIDEO_GAMES)
                .then()
                .body(RestAssuredMatchers.matchesXsdInClasspath("VideoGameXSD.xsd"));
    }

    @Test
    public void testVideoGameSchemaJson() {
        given()
                .pathParam("videoGameId", 5)
                .accept("application/json")
                .when()
                .get(VideoGameEndpoints.SINGLE_VIDEO_GAMES)
                .then()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("VideoGameJsonSchema.json"));
    }

    @Test
    public void captureResponseTime(){
       long responseTime = get(VideoGameEndpoints.ALL_VIDEO_GAMES).time();
        System.out.println("The response time is: "+ responseTime);
    }

    @Test
    public void assertOnResponseTime(){
        get(VideoGameEndpoints.ALL_VIDEO_GAMES)
                .then().time(lessThan(1000L));
    }

}
