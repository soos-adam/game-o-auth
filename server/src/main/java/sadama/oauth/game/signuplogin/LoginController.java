package sadama.oauth.game.signuplogin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/")
    public String getRoot() {
        return "Hello World";
    }
}
