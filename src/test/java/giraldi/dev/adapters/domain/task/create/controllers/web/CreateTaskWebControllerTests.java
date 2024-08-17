package giraldi.dev.adapters.domain.task.create.controllers.web;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import giraldi.dev.adapters.common.models.web.WebRequestModel;
import giraldi.dev.adapters.domain.task.create.models.web.CreateTaskWebBodyModel;
import giraldi.dev.application.domain.task.create.boundaries.CreateTaskInputBoundary;
import giraldi.dev.entities.domain.task.TaskStatus;

@ExtendWith(MockitoExtension.class)
public class CreateTaskWebControllerTests {

    @InjectMocks
    private CreateTaskWebController controller;

    @Mock
    private CreateTaskInputBoundary inputBoundary;

    @Test
    public void executeShouldCallInputBoundaryWithRequestModel() {
        WebRequestModel<Void, Void, CreateTaskWebBodyModel, Void> requestModel = new WebRequestModel<>();
        CreateTaskWebBodyModel bodyModel = new CreateTaskWebBodyModel("Task 1", "Task 1 description", LocalDate.now(),
                TaskStatus.TODO);
        requestModel.body = Optional.of(bodyModel);

        controller.execute(requestModel);
    }
}
