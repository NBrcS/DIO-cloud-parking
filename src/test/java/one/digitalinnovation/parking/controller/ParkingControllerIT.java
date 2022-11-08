package one.digitalinnovation.parking.controller;

import io.restassured.RestAssured;
import one.digitalinnovation.parking.controller.dto.ParkingCreateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingControllerIT extends AbstractContainerBase {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest(){
        RestAssured.port = randomPort;
    }

    @Test
    void whenFindAllThenCheckResult(){
        RestAssured.given()
                .auth()
                .basic("user", "123")
                .when()
                .get("/parking")
                .then()
                .statusCode(200)
                .extract().response().body().prettyPrint();

    }

    @Test
    void whenCreateThenCheckResult(){

        ParkingCreateDTO createDTO = new ParkingCreateDTO(
                "AAA-1234",
                "FL",
                "FD MUSTANG",
                "PURPLE");

        RestAssured.given()
                .auth()
                .basic("user", "123")
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createDTO)
                .post("/parking")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract().response().body().prettyPrint();
    }
}
