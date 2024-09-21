package sadama.oauth.game.api.signuplogin;

import sadama.oauth.game.api.data.IResourceOwner;

public interface ILoginHandler<T extends IResourceOwner> {
    boolean validateInput(Object input);


}
