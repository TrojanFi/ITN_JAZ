package pl.edu.pjwstk.jaz.Authorization;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class AuthenticationService {

     final UserSession userSession;
     final Users users;

    public AuthenticationService(UserSession userSession, Users users) {
        this.userSession = userSession;
        this.users = users;
    }

    public boolean login(String username, String password) {

        if (users.NameSame(username)) {
            if (users.PasswordSame(username, password)) {
                 userSession.logIn();
                 return true;
            }
        }
        return false;
    }
}
