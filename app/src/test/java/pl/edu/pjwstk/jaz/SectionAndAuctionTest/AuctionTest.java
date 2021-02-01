package pl.edu.pjwstk.jaz.SectionAndAuctionTest;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.Authorization.LoginRequest;
import pl.edu.pjwstk.jaz.Authorization.RegisterRequest;
import pl.edu.pjwstk.jaz.IntegrationTest;
import pl.edu.pjwstk.jaz.Requests.AuctionRequest;
import pl.edu.pjwstk.jaz.Requests.CategoryRequest;
import pl.edu.pjwstk.jaz.Requests.SectionNameRequest;
import pl.edu.pjwstk.jaz.Requests.SectionRequest;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@IntegrationTest
public class AuctionTest {

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

  public Response adminLogging(){
    return given()
              .body(new LoginRequest("Admin","Admin"))
              .contentType(ContentType.JSON)
              .post("/api/login")
              .thenReturn();
  }

    public Response userLogging(){
        return given()
                .body(new LoginRequest("User","User"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
    }

    @Test
    public void adminAddAuctionTest403() {
        // @formatter:off
        given()
                .body(new SectionRequest("A",Arrays.asList("","")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addSection");
        given()
                .body(new SectionRequest("A",Arrays.asList("K1","K2")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addCategory");
        given()
                .body(new AuctionRequest("Comment1","Stuff1",1,"K1",Arrays.asList("P1","P2"),Arrays.asList("V1","V2"),Arrays.asList("Pa1","Pa2")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addAuction")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
        // @formatter:on
    }
    @Test
    public void userAddAuctionTest200() {
        // @formatter:off
        given()
                .body(new SectionRequest("A",Arrays.asList("","")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addSection");
        given()
                .body(new SectionRequest("A",Arrays.asList("K1","K2")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addCategory");
        given()
                .body(new AuctionRequest("Comment1","Stuff1",1,"K1",Arrays.asList("P1","P2"),Arrays.asList("V1","V2"),Arrays.asList("Pa1","Pa2")))
                .contentType(ContentType.JSON)
                .cookies(userLogging().getCookies())
                .post("/api/addAuction")
                .then()
                .statusCode(HttpStatus.OK.value());
        // @formatter:on
    }
}