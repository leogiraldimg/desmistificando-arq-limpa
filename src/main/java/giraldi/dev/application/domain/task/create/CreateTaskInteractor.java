package giraldi.dev.application.domain.task.create;

import java.time.LocalDateTime;

import giraldi.dev.application.domain.task.create.boundaries.CreateTaskInputBoundary;
import giraldi.dev.application.domain.task.create.boundaries.CreateTaskOutputBoundary;
import giraldi.dev.application.domain.task.create.gateways.CreateTaskDsGateway;
import giraldi.dev.application.domain.task.create.models.CreateTaskDsRequestModel;
import giraldi.dev.application.domain.task.create.models.CreateTaskDsResponseModel;
import giraldi.dev.application.domain.task.create.models.CreateTaskRequestModel;
import giraldi.dev.application.domain.task.create.models.CreateTaskResponseModel;
import giraldi.dev.entities.domain.task.Task;
import giraldi.dev.entities.domain.task.TaskFactory;

public class CreateTaskInteractor implements CreateTaskInputBoundary {

    private CreateTaskOutputBoundary outputBoundary;
    private CreateTaskDsGateway dsGateway;
    private TaskFactory taskFactory;

    public CreateTaskInteractor(CreateTaskOutputBoundary outputBoundary, CreateTaskDsGateway dsGateway,
            TaskFactory taskFactory) {
        this.outputBoundary = outputBoundary;
        this.dsGateway = dsGateway;
        this.taskFactory = taskFactory;
    }

    @Override
    public void execute(CreateTaskRequestModel requestModel) {
        Task task = taskFactory.create(requestModel.title, requestModel.description, requestModel.dueDate,
                requestModel.status);

        if (!task.isTitleValid()) {
            outputBoundary
                    .presentInvalidAttribute("Título de tarefa inválido. Título deve conter pelo menos 3 caracteres");
            return;
        }

        if (!task.isDescriptionValid()) {
            outputBoundary.presentInvalidAttribute(
                    "Descricão de tarefa inválida. Descricão deve conter pelo menos 10 caracteres");
            return;
        }

        if (!task.isDueDateValid()) {
            outputBoundary.presentInvalidAttribute("Data de vencimento inválida. Data deve ser posterior a data atual");
            return;
        }

        CreateTaskDsResponseModel dsResponseModel = dsGateway.save(new CreateTaskDsRequestModel(requestModel.title,
                requestModel.description, requestModel.dueDate, requestModel.status));

        CreateTaskResponseModel responseModel = new CreateTaskResponseModel(dsResponseModel.id, dsResponseModel.title,
                dsResponseModel.description, dsResponseModel.dueDate.toString(), dsResponseModel.status);

        outputBoundary.presentSuccess(responseModel);
    }
}
