package giraldi.dev.adapters.domain.task.create.controllers.web;

import giraldi.dev.adapters.common.models.web.WebRequestModel;
import giraldi.dev.adapters.domain.task.create.models.web.CreateTaskWebBodyModel;
import giraldi.dev.application.domain.task.create.boundaries.CreateTaskInputBoundary;
import giraldi.dev.application.domain.task.create.models.CreateTaskRequestModel;
import giraldi.dev.entities.domain.task.TaskStatus;

import java.time.LocalDate;

public class CreateTaskWebController {

    private CreateTaskInputBoundary inputBoundary;

    public CreateTaskWebController(CreateTaskInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void execute(WebRequestModel<Void, Void, CreateTaskWebBodyModel, Void> requestModel) {
        CreateTaskWebBodyModel bodyModel = requestModel.body;
        inputBoundary
                .execute(new CreateTaskRequestModel(bodyModel.title, bodyModel.description, LocalDate.parse(bodyModel.dueDate),
                        TaskStatus.valueOf(bodyModel.status)));
    }
}
