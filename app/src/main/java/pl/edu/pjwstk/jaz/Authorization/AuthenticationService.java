package pl.edu.pjwstk.jaz.Authorization;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class AuthenticationService {

    RegisterRequest registerRequest = new RegisterRequest();
    final UserSession userSession;

    public AuthenticationService(UserSession userSession) {
        this.userSession = userSession;
    }

    public boolean login(String username, String password) {


        return false;
    }
}
