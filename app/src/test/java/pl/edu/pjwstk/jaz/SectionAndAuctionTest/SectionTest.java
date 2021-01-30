package pl.edu.pjwstk.jaz.SectionAndAuctionTest;




import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.Authorization.LoginRequest;
import pl.edu.pjwstk.jaz.Authorization.RegisterRequest;
import pl.edu.pjwstk.jaz.IntegrationTest;
import pl.edu.pjwstk.jaz.Requests.SectionRequest;


import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
@RunWith(SpringRunner.class)
@IntegrationTest
public class SectionTest {

    @BeforeClass
    public static void beforeClassRegisterAdmin(){
        given()
                .body(new RegisterRequest("Admin","Admin"))
                .contentType(ContentType.JSON)
                .post("/api/register");
        given()
                .body(new LoginRequest("Admin","Admin"))
                .contentType(ContentType.JSON)
                .post("/api/login");
    }


//    @Test
//    public void addSectionTestPositive() {
//        // @formatter:off
//        List<String> sections = ;given()
//                .body(new SectionRequest("Admin",a)
//                .contentType(ContentType.JSON)
//                .post("/api/addSection")
//                .then()
//                .statusCode(org.springframework.http.HttpStatus.OK.value());
//        // @formatter:on
//    }


}