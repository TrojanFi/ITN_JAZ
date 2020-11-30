package pl.edu.pjwstk.jaz.Authorization;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AuthenticationService {

     final UserSession userSession;
     final Users users;

    public AuthenticationService(UserSession userSession, Users users) {
        this.userSession = userSession;
        this.users = users;
    }

    public boolean login(String username, String password) {

        if (users.nameExist(username)) {
            if (users.passwordSame(username, password)) {
                    userSession.setPermission(users.getPermission(username));
                    userSession.setUsername(username);
                    userSession.logIn();
                    // Nadanie tokena
                     SecurityContextHolder.getContext().setAuthentication(new AppAuthentication(users.getUser(username)));

                    return true;
            }
        }
        userSession.logOut();
        return false;
    }
}
