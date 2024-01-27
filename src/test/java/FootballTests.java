import config.FootballCongig;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FootballTests extends FootballCongig {
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
                .queryParam("areas" , areasId)
                .when()
                .get("/areas")
                .then();

    }


}
