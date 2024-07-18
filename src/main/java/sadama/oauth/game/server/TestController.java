package sadama.oauth.game.server;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController()
public class TestController {

    @GetMapping("ping")
    public Date ping() {
        return new Date();
    }

    @GetMapping(value = "target" , produces = MediaType.TEXT_HTML_VALUE)
    public String target() {
        return "<h1>Hello</h1><h3>World!</h3>";
    }

    @GetMapping("redirect")
    public void redirect(HttpServletResponse response) {
        response.setStatus(301);
        response.setHeader("Location","/target");

    }

}
