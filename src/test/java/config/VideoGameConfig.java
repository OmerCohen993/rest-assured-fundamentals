package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;

import static org.hamcrest.Matchers.lessThan;

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
                .addFilter(new RequestLoggingFilter()) //log to console all requests
                .addFilter(new ResponseLoggingFilter()) //log to console all responses
                .build();

        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectResponseTime(lessThan(3000L))
                .build();

    }
}
