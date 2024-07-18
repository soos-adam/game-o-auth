package sadama.oauth.game.server;


import jakarta.annotation.PreDestroy;

public class TestClass {
    public TestClass() {
        System.out.println("test class is created");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("test class is destroyed");
    }
}
