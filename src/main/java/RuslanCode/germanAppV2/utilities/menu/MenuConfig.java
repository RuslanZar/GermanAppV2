package RuslanCode.germanAppV2.utilities.menu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MenuConfig {

    @Bean
    public Menu menu() {
        return new Menu();
    }
}
