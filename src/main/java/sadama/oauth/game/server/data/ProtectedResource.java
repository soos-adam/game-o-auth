package sadama.oauth.game.server.data;

import java.util.List;

public class ProtectedResource {
    private ResourceOwner owner;
    private String name;
    private List<Scope> scopes;
}
