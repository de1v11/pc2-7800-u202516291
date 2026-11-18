package upc.edu.pe.shared.infraestructure.configuration.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI logisticPlatformOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Logistic Platform API")
                        .version("1.0.0")
                        .description("REST API documentation for the Logistic Platform bounded contexts.")
                        .contact(new Contact().name("Logistic Platform Team")));

    }
}
