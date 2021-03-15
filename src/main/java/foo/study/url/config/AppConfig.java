package foo.study.url.config;

import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ConcurrentHashMap<String, String> userLists() {
        System.out.println("call AppConfig.userLists");
        return new ConcurrentHashMap();
    }
}
