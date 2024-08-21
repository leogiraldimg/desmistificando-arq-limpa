package giraldi.dev.infra.datasource.jpa.domain.task.create;

import giraldi.dev.application.domain.task.create.gateways.CreateTaskDsGateway;
import giraldi.dev.application.domain.task.create.models.CreateTaskDsRequestModel;
import giraldi.dev.application.domain.task.create.models.CreateTaskDsResponseModel;
import giraldi.dev.infra.datasource.jpa.domain.task.TaskDataMapper;
import giraldi.dev.infra.datasource.jpa.domain.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateTaskDsJPA implements CreateTaskDsGateway {

    @Autowired
    private TaskRepository repository;

    @Override
    public CreateTaskDsResponseModel save(CreateTaskDsRequestModel requestModel) {
        TaskDataMapper dataMapper = new TaskDataMapper(requestModel.title,
                requestModel.description,
                requestModel.dueDate,
                requestModel.status);

        TaskDataMapper saved = repository.save(dataMapper);
        return new CreateTaskDsResponseModel(saved.getId().toString(),
                saved.getTitle(),
                saved.getDescription(),
                saved.getDueDate(),
                saved.getStatus());
    }
}
