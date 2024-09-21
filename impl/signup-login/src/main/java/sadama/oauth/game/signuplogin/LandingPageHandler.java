package sadama.oauth.game.signuplogin;


import sadama.oauth.game.api.signuplogin.ILandingPageHandler;

public class LandingPageHandler implements ILandingPageHandler {
    //TODO have the signup-login moved elsewhere
    private static final String BASE = "/signup-login/landing/";

    @Override
    public String getIndexFile() {
        return BASE + "index.html";
    }

    @Override
    public String getResourceDirectory() {
        return BASE;
    }
}
