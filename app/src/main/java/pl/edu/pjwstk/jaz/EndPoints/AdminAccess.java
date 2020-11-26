package pl.edu.pjwstk.jaz.EndPoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminAccess {
    @GetMapping("/admin")
    public void Admin(){

    }


}
