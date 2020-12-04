package pl.edu.pjwstk.jaz.LoginRegister;


import junit.framework.Assert;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import pl.edu.pjwstk.jaz.IntegrationTest;

import static io.restassured.RestAssured.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@IntegrationTest
public class UserTest {

    private MockMvc mockMvc;


    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Before
    public void registerAdmin() throws Exception {
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"admin\",\"password\" : \"admin\"}"))
                .andExpect(status().isOk());
    }
    @Before
    public void registerUser() throws Exception {
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"user\",\"password\" : \"user\"}"))
                .andExpect(status().isOk());
    }
    @Before
    public void loginUser() throws Exception {
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"user\",\"password\" : \"user\"}"))
                .andExpect(status().isOk());
    }
    @Test
    public void registerUserTest() throws Exception {
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"user\",\"password\" : \"user\"}"))
                .andExpect(status().isOk());
    }
    @Test
    public void loginUserTest() throws Exception {
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"user\",\"password\" : \"user\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void loginUserTestBad() throws Exception {
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"userbad\",\"password\" : \"userbad\"}"))
                .andExpect(status().isUnauthorized());
    }
    @Test
    public void openUserPage() throws Exception {
        mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().isOk());
    }
    @Test
    public void openEveryonePage() throws Exception {
        mockMvc.perform(get("/auth0/is-ready")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().isOk());
    }
    @Test
    public void openAdminPage() {
        try {
            mockMvc.perform(get("/admin")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(""))
                    .andExpect(status().isOk());
        }catch (Exception e){
            Assert.assertTrue(e.getCause() instanceof AccessDeniedException);
        }
    }
//    @Test
//    public void openAdminPage() {
//        // @formatter:off
//        given()
//                .get("/api/admin")
//                .then()
//                .statusCode(HttpStatus.SC_OK);
//        // @formatter:on
//
//    }
//    @Test
//    public void openUserPage() {
//        // @formatter:off
//        given()
//                .get("/api/users")
//                .then()
//                .statusCode(HttpStatus.SC_FORBIDDEN);
//        // @formatter:on
//
//    }







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