package pl.edu.pjwstk.jaz.Authorization;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.DataBase.UserEntity;
import pl.edu.pjwstk.jaz.DataBase.UserService;

@RestController
public class RegisterController {

    private final Users users;
    private final UserService userService;

    public RegisterController(Users users, UserService userService) {
        this.users = users;
        this.userService = userService;
    }

    @PostMapping("register")
    public void register(@RequestBody RegisterRequest registerRequest) {
        String permission = "User";
        //zarejestrowac
        if(users.isEmpty()) {
            permission = "Admin";
            User user = new User(registerRequest.getUsername(), registerRequest.getPassword());
            user.addAuthorities(permission);
            users.add(user);

            userService.saveUser(registerRequest.getUsername(),registerRequest.getPassword());
            UserEntity singleResult = userService.findUserByUsername(registerRequest.getUsername());
            System.out.println("add admin" + singleResult);
            }
        else {
            if (!users.nameExist(registerRequest.getUsername())){
                User user = new User(registerRequest.getUsername(), registerRequest.getPassword());
              user.addAuthorities(permission);
                users.add(user);
                System.out.println("add user");
            }
        }
    }
    // remove
    @GetMapping("/register")
    public java.util.ArrayList<User> usersInHashMap() {
        return users.usersInHashMap();
    }
}