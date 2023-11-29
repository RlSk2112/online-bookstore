package bg.softuni.bookstore.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

@TestConfiguration
@Import(value = {AppConfig.class})
public class TestConfig {
}
