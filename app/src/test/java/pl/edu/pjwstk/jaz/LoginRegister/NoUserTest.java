package pl.edu.pjwstk.jaz.LoginRegister;




import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.IntegrationTest;
import static io.restassured.RestAssured.given;


@RunWith(SpringRunner.class)
@IntegrationTest
public class NoUserTest {



    @Test
    public void openAdminPage() {
        // @formatter:off
        given()
                .get("/api/admin")
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN);
        // @formatter:on

    }
    @Test
    public void openUserPage() {
        // @formatter:off
        given()
                .get("/api/users")
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN);
        // @formatter:on

    }
    @Test
    public void openEveryonePage() {
        // @formatter:off
        given()
                .get("/api/auth0/is-ready")
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
        // @formatter:on

    }







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

//    @Test
//    public void admin_login_is_ready() {
//        // @formatter:off
//        given()
//                .body(new RegisterRequest("A","A"))
//                .when()
//                .post("/api/register")
//                .thenReturn();
//        var response =    given()
//                .body(new LoginRequest("A","A"))
//                .post("/api/login")
//                .thenReturn();
//        given()
//                .cookies(response.getCookies())
//                .get("/api/is-ready")
//                .then()
//                .statusCode(HttpStatus.SC_OK);
//        // @formatter:on
//
//    }

}