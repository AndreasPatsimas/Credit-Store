package gr.pwc.assignment.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@SecurityScheme(name = "JWT", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class SwaggerConfig {
    //Visit http://localhost:8081/assignment/api/swagger-ui.html for Swagger-UI

    @Bean
    public GroupedOpenApi allApi() {
        return GroupedOpenApi.builder()
                .group("all")
                .addOpenApiCustomizer(openApi -> openApi.addSecurityItem(securityRequirement()).info(apiInfo()))
                .pathsToMatch("/**")
                .build();
    }

    private Info apiInfo() {
        return new Info()
                .title("Assignment Service API definition")
                .version("1.0.0");
    }

    private SecurityRequirement securityRequirement() {
        return new SecurityRequirement()
                .addList("JWT", Arrays.asList("global", "accessEverything"));
    }
}
