package pl.edu.pjwstk.jaz.EndPoints;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAccess {
    @PreAuthorize("hasRole('User')")
    @GetMapping("auth0/users")
    public void users(){

    }
}
