package apiTest;

import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestUser {
    String ct = "application/json"; // contentType
    String uriUser = "https://petstore.swagger.io/v2/user/";

    public static String lerArquivoJson(String arquivoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(arquivoJson)));
    }

    @Test
    @Order(1)
    public void testarIncluirUser() throws IOException {
        String jsonBody = lerArquivoJson("src/test/resources/json/user1.json");
        String userId = "29682";

        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .post(uriUser)
        .then()
                .log().all()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is(userId))
        ;
    }

    @Test
    @Order(2)
    public void testarConsultarUser(){
        String username = "dri";
        int userId = 29682;
        String email = "adri@adri.com";

        given()
                .contentType(ct)
                .log().all()
        .when()
                .get(uriUser + username)
        .then()
                .log().all()
                .statusCode(200)
                .body("id", is(userId))
                .body("email", is(email));
    }

    @Test
    @Order(3)
    public void testarAtualizarUser() throws IOException {
        String username = "dri";
        String userId = "29682";
        String jsonBody = lerArquivoJson("src/test/resources/json/user2.json");
        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .put(uriUser + username)
        .then()
                .log().all()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is(userId));
    }

    @Test
    @Order(4)
    public void testarDeletarUser(){
        String username = "dri";

        given()
                .contentType(ct)
                .log().all()
        .when()
                .delete(uriUser + username)
        .then()
                .log().all()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is(username));
    }

    @Test
    @Order(5)
    public void testarLogin(){
        String username = "dri";
        String password = "123456";


        Response response = (Response) given()
                .contentType(ct)
                .log().all()
        .when()
                .get(uriUser + "login?username=" + username + "&password=" + password)
        .then()
                .log().all()
                .statusCode(is(200))
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", containsString("logged in user session:"))
                .body("message", hasLength(36))
                .extract();

        String message = response.jsonPath().getString("message");
        String token = message.split(":")[1];

        System.out.println("Token: " + token);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/massaUser.csv", numLinesToSkip = 1)
    public void testarIncluirUserCSV(
            String id,
            String username,
            String firstName,
            String lastName,
            String email,
            String password,
            String phone,
            String userStatus) {
        StringBuilder json = new StringBuilder("{");
        json.append("\"id\": ").append(id).append(",");
        json.append("\"username\": \"").append(username).append("\",");
        json.append("\"firstName\": \"").append(firstName).append("\",");
        json.append("\"lastName\": \"").append(lastName).append("\",");
        json.append("\"email\": \"").append(email).append("\",");
        json.append("\"password\": \"").append(password).append("\",");
        json.append("\"phone\": \"").append(phone).append("\",");
        json.append("\"userStatus\": ").append(userStatus);
        json.append("}");

        given()
                .contentType(ct)
                .log().all()
                .body(json.toString())
        .when()
                .post(uriUser)
        .then()
                .log().all()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is(id))
        ;
    }
}






