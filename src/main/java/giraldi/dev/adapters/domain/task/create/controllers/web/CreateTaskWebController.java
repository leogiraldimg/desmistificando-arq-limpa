package giraldi.dev.adapters.domain.task.create.controllers.web;

import giraldi.dev.adapters.common.models.web.WebRequestModel;
import giraldi.dev.adapters.domain.task.create.models.web.CreateTaskWebBodyModel;
import giraldi.dev.application.domain.task.create.boundaries.CreateTaskInputBoundary;
import giraldi.dev.application.domain.task.create.models.CreateTaskRequestModel;

public class CreateTaskWebController {

    private CreateTaskInputBoundary inputBoundary;

    public CreateTaskWebController(CreateTaskInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void execute(WebRequestModel<Void, Void, CreateTaskWebBodyModel, Void> requestModel) {
        CreateTaskWebBodyModel bodyModel = requestModel.body.get();
        inputBoundary
                .execute(new CreateTaskRequestModel(bodyModel.title, bodyModel.description, bodyModel.dueDate,
                        bodyModel.status));
    }
}
