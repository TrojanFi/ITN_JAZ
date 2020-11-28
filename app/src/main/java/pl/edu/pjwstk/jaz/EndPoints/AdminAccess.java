package pl.edu.pjwstk.jaz.EndPoints;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("hasAuthority('Admin')")
@RestController
public class AdminAccess {
    @GetMapping("auth0/admin")
    public void Admin(){

    }


}
