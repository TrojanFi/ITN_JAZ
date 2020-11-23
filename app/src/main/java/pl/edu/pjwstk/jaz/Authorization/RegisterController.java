package pl.edu.pjwstk.jaz.Authorization;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class RegisterController {

    private final Users users;

    public RegisterController(Users users) {
        this.users = users;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest) {
        //zarejestrowac
        User user = new User(registerRequest.getUsername(),registerRequest.getPassword());
        users.add(user);

    }
}