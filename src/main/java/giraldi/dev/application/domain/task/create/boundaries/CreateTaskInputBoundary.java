package giraldi.dev.application.domain.task.create.boundaries;

import giraldi.dev.application.domain.task.create.models.CreateTaskRequestModel;

public interface CreateTaskInputBoundary {

    void execute(CreateTaskRequestModel requestModel);
}
