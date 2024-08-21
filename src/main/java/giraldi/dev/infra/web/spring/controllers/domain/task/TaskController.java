package giraldi.dev.infra.web.spring.controllers.domain.task;

import giraldi.dev.adapters.common.models.web.WebRequestModel;
import giraldi.dev.adapters.domain.task.create.controllers.web.CreateTaskWebController;
import giraldi.dev.adapters.domain.task.create.models.web.CreateTaskWebBodyModel;
import giraldi.dev.adapters.domain.task.create.presenters.web.CreateTaskWebPresenter;
import giraldi.dev.application.domain.task.create.CreateTaskInteractor;
import giraldi.dev.application.domain.task.create.models.CreateTaskResponseModel;
import giraldi.dev.entities.domain.task.CommonTaskFactory;
import giraldi.dev.infra.datasource.jpa.domain.task.create.CreateTaskDsJPA;
import giraldi.dev.infra.web.spring.controllers.common.SpringWebHandler;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {

    @Autowired
    private ApplicationContext applicationContext;

    @PostMapping
    public void insert(HttpServletResponse response, @RequestBody CreateTaskWebBodyModel body) {
        CreateTaskWebController webController = new CreateTaskWebController(
                new CreateTaskInteractor(
                        new CreateTaskWebPresenter(new SpringWebHandler<CreateTaskResponseModel>(response)),
                        applicationContext.getBean(CreateTaskDsJPA.class),
                        new CommonTaskFactory()
                ));
        WebRequestModel<Void, Void, CreateTaskWebBodyModel, Void> requestModel = new WebRequestModel<Void, Void, CreateTaskWebBodyModel, Void>();
        requestModel.body = body;
        webController.execute(requestModel);
    }
}
