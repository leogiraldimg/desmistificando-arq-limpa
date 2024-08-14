package giraldi.dev.application.domain.task.create.gateways;

import giraldi.dev.application.domain.task.create.models.CreateTaskDsRequestModel;
import giraldi.dev.application.domain.task.create.models.CreateTaskDsResponseModel;

public interface CreateTaskDsGateway {

    CreateTaskDsResponseModel save(CreateTaskDsRequestModel requestModel);
}
