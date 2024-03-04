package hsos.swa;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PlayerResourceTest {
    @Test
    public void shouldGetAllPlayers() {
        given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when().get("api/players")
                .then()
                .statusCode(200)
                .body("size()", is(3));
    }
}