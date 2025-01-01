package sadama.oauth.goa.landingpage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sadama.oauth.goa.api.landingpage.ILandingPageHandler;

@Configuration
public class LandingPageConfiguration {

    @Bean
    public ILandingPageHandler goaLandingPageHandler() {
        return new LandingPageHandler();
    }
}
