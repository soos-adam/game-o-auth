package sadama.oauth.goa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import sadama.oauth.goa.common.Constants;

@SpringBootApplication
@ComponentScan(Constants.Configuration.SPRING_PACKAGE_BASE)
public class ServerLauncher {
    public static void main(String[] args) {
        SpringApplication.run(ServerLauncher.class, args);
    }
}
