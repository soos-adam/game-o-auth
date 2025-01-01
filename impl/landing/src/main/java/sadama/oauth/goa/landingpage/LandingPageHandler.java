package sadama.oauth.goa.landingpage;


import sadama.oauth.goa.api.landingpage.ILandingPageHandler;

public class LandingPageHandler implements ILandingPageHandler {
    private static final String BASE = "/landing/";

    @Override
    public String getIndexFile() {
        return BASE + "index.html";
    }

    @Override
    public String getResourceDirectory() {
        return BASE + "res/";
    }
}
