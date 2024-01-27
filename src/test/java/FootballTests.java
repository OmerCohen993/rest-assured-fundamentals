import config.FootballConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class FootballTests extends FootballConfig {
    @Test
    public void getDetailsOfOneArea() {
        given()
                .queryParam("areas", 2007)
                .when()
                .get("/areas")
                .then();

    }


    @Test
    public void getDetailsOfMultipleAreas() {
        String areasId = "200,2001";
        given()
                .urlEncodingEnabled(false) //doesn't need
                .queryParam("areas", areasId)
                .when()
                .get("/areas")
                .then();

    }

    @Test
    public void getDataFounded() {
        given()
                .when()
                .get("teams/57")
                .then()
                .body("founded", equalTo(1886));
    }

    @Test
    public void getFirstTeamName() {
        given()
                .when()
                .get("competitions/2021/teams")
                .then()
                .body("teams.name[0]", equalTo("Arsenal FC"));
    }

    @Test
    public void getAllTeamData() {
        String responseBody = get("teams/57").asString();
        System.out.println(responseBody);
    }

    @Test
    public void getAllTeamData_DoCheckFirst() {
        Response response =
                given()
                        .when()
                        .get("teams/57")
                        .then()
                        .contentType(ContentType.JSON)
                        .extract().response();
        String jsonResponseAsString = response.asString();
        System.out.println(jsonResponseAsString);
    }

}
