package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;

public class VideoGameConfig {
    @BeforeClass
    public static void setup(){
//        RestAssured.baseURI = "https://videogamedb.uk/";
//        RestAssured.basePath = "api/v2/";
//        RestAssured.port = 443;
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://videogamedb.uk/")
                .setBasePath("api/v2/")
                .setContentType("application/json")
                .addHeader("Accept", "application/json")
                .build();

        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();

    }
}
