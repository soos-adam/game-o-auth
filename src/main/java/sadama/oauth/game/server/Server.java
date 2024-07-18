package sadama.oauth.game.server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(
        basePackages = Constants.Configuration.SPRING_PACKAGE_BASE
)
@SpringBootApplication
public class Server {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(Server.class)
                .build()
                .run();
    }

    @Bean
    public TestClass t() {
        return new TestClass();
    }
}
