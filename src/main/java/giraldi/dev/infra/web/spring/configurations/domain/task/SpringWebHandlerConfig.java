package giraldi.dev.infra.web.spring.configurations.domain.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import giraldi.dev.infra.web.spring.controllers.common.SpringWebHandler;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SpringWebHandlerConfig<T> {

    @Bean
    public SpringWebHandler<T> createSpringWebHandler(HttpServletResponse response) {
        return new SpringWebHandler<T>(response);
    }
}
