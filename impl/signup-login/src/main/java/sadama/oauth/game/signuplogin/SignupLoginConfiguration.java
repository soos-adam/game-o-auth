package sadama.oauth.game.signuplogin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sadama.oauth.game.api.signuplogin.ILandingPageHandler;

@Configuration
public class SignupLoginConfiguration {

    @Bean
    public ILandingPageHandler landingPageHandler() {
        return new LandingPageHandler();
    }
}
