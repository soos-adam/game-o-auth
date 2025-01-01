package sadama.oauth.goa.api.signuplogin;

import sadama.oauth.goa.api.data.IResourceOwner;

public interface ISignupLoginHandler<T extends IResourceOwner> {
    boolean validateInput(Object input);
    T getResourceOwner(Object input);
    boolean verifyCredentials();
    ILoginPageHandler getLoginPageHandler();
    ISignupPageHandler getSignupPageHandler();

}
