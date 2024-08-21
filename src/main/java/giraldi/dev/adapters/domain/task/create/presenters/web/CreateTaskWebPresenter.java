package giraldi.dev.adapters.domain.task.create.presenters.web;

import java.time.Instant;

import giraldi.dev.adapters.common.handlers.web.WebHandler;
import giraldi.dev.adapters.common.models.web.WebViewModel;
import giraldi.dev.application.domain.task.create.boundaries.CreateTaskOutputBoundary;
import giraldi.dev.application.domain.task.create.models.CreateTaskResponseModel;

public class CreateTaskWebPresenter implements CreateTaskOutputBoundary {

    private WebHandler<CreateTaskResponseModel> handler;

    public CreateTaskWebPresenter(WebHandler<CreateTaskResponseModel> handler) {
        this.handler = handler;
    }

    @Override
    public void presentSuccess(CreateTaskResponseModel responseModel) {
        WebViewModel<CreateTaskResponseModel> viewModel = new WebViewModel<CreateTaskResponseModel>(200, false,
                "Tarefa cadastrada com sucesso", responseModel,
                Instant.now());

        handler.send(viewModel);
    }

    @Override
    public void presentInvalidAttribute(String error) {
        WebViewModel<CreateTaskResponseModel> viewModel = new WebViewModel<CreateTaskResponseModel>(400, true, error,
                null, Instant.now());

        handler.send(viewModel);
    }
}
