package giraldi.dev.infra.web.spring.controllers.domain.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giraldi.dev.adapters.common.models.web.WebRequestModel;
import giraldi.dev.adapters.domain.task.create.controllers.web.CreateTaskWebController;
import giraldi.dev.adapters.domain.task.create.models.web.CreateTaskWebBodyModel;
import giraldi.dev.infra.web.spring.controllers.domain.task.models.CreateTaskBodyModel;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {

    @Autowired
    private ApplicationContext applicationContext;

    @PostMapping
    public void insert(HttpServletResponse response, @Valid @RequestBody CreateTaskBodyModel body) {
        CreateTaskWebController webController = applicationContext.getBean(CreateTaskWebController.class, response);
        CreateTaskWebBodyModel webBodyModel = new CreateTaskWebBodyModel(body.title, body.description, body.dueDate,
                body.status);
        WebRequestModel<Void, Void, CreateTaskWebBodyModel, Void> requestModel = new WebRequestModel<Void, Void, CreateTaskWebBodyModel, Void>();
        requestModel.body = webBodyModel;
        webController.execute(requestModel);
    }
}
