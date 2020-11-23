package pl.edu.pjwstk.jaz.Authorization;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Users {

    HashMap<String,User> userHashMap = new HashMap<String,User>();

    public void add(User user)
    {
        userHashMap.put(user.getUsername(),user);
    }

    public String getUsername(String username)
    {
        return userHashMap.get(username).getUsername();
    }

    public boolean NameSame(String username)
    {
        return username.equals(userHashMap.get(username).getUsername());
    }

    public boolean PasswordSame(String username, String password)
    {
        return  password.equals(userHashMap.get(username).getPassword());
    }
}
