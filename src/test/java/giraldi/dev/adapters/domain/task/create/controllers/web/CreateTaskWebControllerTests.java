package giraldi.dev.adapters.domain.task.create.controllers.web;

import giraldi.dev.adapters.common.models.web.WebRequestModel;
import giraldi.dev.adapters.domain.task.create.models.web.CreateTaskWebBodyModel;
import giraldi.dev.application.domain.task.create.boundaries.CreateTaskInputBoundary;
import giraldi.dev.application.domain.task.create.models.CreateTaskRequestModel;
import giraldi.dev.entities.domain.task.TaskStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CreateTaskWebControllerTests {

    @InjectMocks
    private CreateTaskWebController controller;

    @Mock
    private CreateTaskInputBoundary inputBoundary;

    @Test
    public void executeShouldCallInputBoundaryWithRequestModel() {
        WebRequestModel<Void, Void, CreateTaskWebBodyModel, Void> webRequestModel = new WebRequestModel<>();
        CreateTaskWebBodyModel bodyModel = new CreateTaskWebBodyModel("Task 1", "Task 1 description", LocalDate.now().toString(),
                TaskStatus.TODO.toString());
        webRequestModel.body = bodyModel;

        controller.execute(webRequestModel);

        verify(inputBoundary).execute(new CreateTaskRequestModel(bodyModel.title,
                bodyModel.description,
                LocalDate.parse(bodyModel.dueDate),
                TaskStatus.valueOf(bodyModel.status)));
    }
}
