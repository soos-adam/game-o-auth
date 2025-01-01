package sadama.oauth.goa.signup;

import sadama.oauth.goa.api.signuplogin.ISignupPageHandler;

public class SignupPageHandler implements ISignupPageHandler {
    private static final String BASE = "/signup/";

    @Override
    public String getIndexFile() {
        return BASE + "index.html";
    }

    @Override
    public String getResourceDirectory() {
        return BASE + "res/";
    }
}
