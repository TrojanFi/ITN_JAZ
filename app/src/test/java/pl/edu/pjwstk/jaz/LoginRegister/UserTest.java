package pl.edu.pjwstk.jaz.LoginRegister;


import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.Authorization.LoginRequest;
import pl.edu.pjwstk.jaz.Authorization.RegisterRequest;
import pl.edu.pjwstk.jaz.IntegrationTest;

import static io.restassured.RestAssured.given;
@RunWith(SpringRunner.class)
@IntegrationTest
public class UserTest {


    @BeforeClass
    public static void beforeClassRegisterAdmin(){
        given()
                .body(new RegisterRequest("Admin","Admin"))
                .contentType(ContentType.JSON)
                .post("/api/register");
        given()
                .body(new RegisterRequest("User","User"))
                .contentType(ContentType.JSON)
                .post("/api/register");

    }

    @Test
    public void adminLoginOnAdminPage() {
        // @formatter:off
        var response =given()
                .body(new LoginRequest("Admin","Admin"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/admin")
                .then()
                .statusCode(HttpStatus.OK.value());
        // @formatter:on

    }

    @Test
    public void adminLoginOnIsReadyPage() {
        // @formatter:off
        var response =given()
                .body(new LoginRequest("Admin","Admin"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/auth0/is-ready")
                .then()
                .statusCode(HttpStatus.OK.value());
        // @formatter:on

    }

}