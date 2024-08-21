package giraldi.dev.infra.web.spring.configurations.domain.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import giraldi.dev.adapters.domain.task.create.controllers.web.CreateTaskWebController;
import giraldi.dev.adapters.domain.task.create.presenters.web.CreateTaskWebPresenter;
import giraldi.dev.application.domain.task.create.CreateTaskInteractor;
import giraldi.dev.application.domain.task.create.models.CreateTaskResponseModel;
import giraldi.dev.entities.domain.task.CommonTaskFactory;
import giraldi.dev.infra.datasource.jpa.domain.task.create.CreateTaskDsJPA;
import giraldi.dev.infra.web.spring.controllers.common.SpringWebHandler;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class TaskConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public CreateTaskWebController createTaskWebController(HttpServletResponse response) {
        return new CreateTaskWebController(
                new CreateTaskInteractor(
                        new CreateTaskWebPresenter(new SpringWebHandler<CreateTaskResponseModel>(response)),
                        applicationContext.getBean(CreateTaskDsJPA.class),
                        new CommonTaskFactory()));
    }
}
