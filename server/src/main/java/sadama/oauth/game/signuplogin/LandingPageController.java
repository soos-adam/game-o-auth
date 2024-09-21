package sadama.oauth.game.signuplogin;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sadama.oauth.game.api.signuplogin.ILandingPageHandler;
import sadama.oauth.game.utils.ResourceUtils;

import java.io.IOException;

@RestController
@RequestMapping("/")
public class LandingPageController {
    private ILandingPageHandler handler;

    public LandingPageController(ILandingPageHandler handler) {
        this.handler = handler;
    }

    @GetMapping
    public String getLandingPage() throws IOException {
        //TODO map to response
        return ResourceUtils.readResourceFileContent(handler.getIndexFile());
    }

    @GetMapping("res/**")
    public String getResource(HttpServletRequest request) throws IOException {
        String resource = request.getRequestURI().split("/res")[1];
        return ResourceUtils.readResourceFileContent(handler.getResourceDirectory() + resource);

    }

}
