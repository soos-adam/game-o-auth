package sadama.oauth.goa.landingpage;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sadama.oauth.goa.api.landingpage.ILandingPageHandler;
import sadama.oauth.goa.utils.ResourceUtils;
import sadama.oauth.goa.utils.ResponseUtils;

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

    @GetMapping("/res/**")
    public ResponseEntity<String> getResource(HttpServletRequest request) throws IOException {
        String resource = request.getRequestURI().split("/res")[1];
        String resourceContent = ResourceUtils.readResourceFileContent(handler.getResourceDirectory() + resource);
        return ResponseUtils.createResponse(resourceContent, resource);
    }

}
