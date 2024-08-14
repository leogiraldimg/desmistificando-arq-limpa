package giraldi.dev.application.domain.task.create.boundaries;

import giraldi.dev.application.domain.task.create.models.CreateTaskResponseModel;

public interface CreateTaskOutputBoundary {
    void presentSuccess(CreateTaskResponseModel responseModel);

    void presentInvalidAttribute(String error);
}
