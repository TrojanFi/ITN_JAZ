package pl.edu.pjwstk.jaz;


import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.Authorization.*;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@IntegrationTest
public class LoginTest {


//    @BeforeClass
//    public static void beforeClassRegisterAdmin() throws Exception {
//            given()
//                .body(new RegisterRequest("A","A"))
//                .when()
//                .post("/api/register")
//                .thenReturn();
//    }
//    @BeforeClass
//    public static void beforeClassRegisterUser() throws Exception {
//        given()
//                .body(new RegisterRequest("U","U"))
//                .when()
//                .post("/api/register")
//                .thenReturn();
//    }

//    @Test
//    public void admin_login() {
//        // @formatter:off
//        var response = given()
//                .body(new LoginRequest("A","A"))
//                .post("/api/login")
//                .thenReturn();
//        given()
//                .cookies(response.getCookies())
//                .get("/api/admin")
//                .then()
//                .statusCode(HttpStatus.SC_OK);
//        // @formatter:on
//
//    }
//
//    @Test
//    public void user_register() {
//        // @formatter:off
//        given()
//                .body(new RegisterRequest("U","U"))
//                .when()
//                .get("/api/register")
//                .then()
//                .statusCode(HttpStatus.SC_OK);
//        // @formatter:on
//
//    }

    @Test
    public void admin_login_is_ready() {
        // @formatter:off
        given()
                .body(new RegisterRequest("A","A"))
                .when()
                .post("/api/register")
                .thenReturn();
        var response =    given()
                .body(new LoginRequest("A","A"))
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/is-ready")
                .then()
                .statusCode(HttpStatus.SC_OK);
        // @formatter:on

    }

}