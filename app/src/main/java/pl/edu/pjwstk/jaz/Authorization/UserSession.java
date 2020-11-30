package pl.edu.pjwstk.jaz.Authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class UserSession {
    private boolean isLogged = false;

    private String permission = "User";
    private String username = "username";

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void logIn(){
        isLogged = true;
    }
    public void logOut(){
        isLogged = false;
    }
    public boolean isLoggedIn() {
        return isLogged;
    }
    //tutaj jakas zmienna informacja
    // która pozwoli okreslic czy uzytkownik jest zalogowany

    //metody do zarzadzania
}