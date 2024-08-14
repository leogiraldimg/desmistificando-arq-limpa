package giraldi.dev.application.domain.task.create;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import giraldi.dev.application.domain.task.create.boundaries.CreateTaskOutputBoundary;
import giraldi.dev.application.domain.task.create.gateways.CreateTaskDsGateway;
import giraldi.dev.application.domain.task.create.models.CreateTaskDsRequestModel;
import giraldi.dev.application.domain.task.create.models.CreateTaskDsResponseModel;
import giraldi.dev.application.domain.task.create.models.CreateTaskRequestModel;
import giraldi.dev.application.domain.task.create.models.CreateTaskResponseModel;
import giraldi.dev.entities.domain.task.Task;
import giraldi.dev.entities.domain.task.TaskFactory;
import giraldi.dev.entities.domain.task.TaskStatus;

@ExtendWith(MockitoExtension.class)
public class CreateTaskInteractorTests {

    @InjectMocks
    private CreateTaskInteractor interactor;

    @Mock
    private CreateTaskOutputBoundary outputBoundary;

    @Mock
    private CreateTaskDsGateway dsGateway;

    @Mock
    private TaskFactory taskFactory;

    @Mock
    private Task task;

    private String id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private TaskStatus taskStatus;
    private CreateTaskRequestModel requestModel;
    private CreateTaskResponseModel responseModel;
    private CreateTaskDsResponseModel dsResponseModel;

    @BeforeEach
    void setUp() {
        title = "Task 1";
        description = "Description 1";
        dueDate = LocalDate.now().plusDays(1);
        taskStatus = TaskStatus.TODO;
        requestModel = new CreateTaskRequestModel(title, description, dueDate, taskStatus);
        responseModel = new CreateTaskResponseModel(id, title, description, dueDate.toString(), taskStatus);
        dsResponseModel = new CreateTaskDsResponseModel(id, title, description, dueDate, taskStatus);

        when(taskFactory.create(title, description, dueDate, taskStatus)).thenReturn(task);
        lenient().when(task.isTitleValid()).thenReturn(true);
        lenient().when(task.isDescriptionValid()).thenReturn(true);
        lenient().when(task.isDueDateValid()).thenReturn(true);
        lenient().when(dsGateway.save(any(CreateTaskDsRequestModel.class))).thenReturn(dsResponseModel);
    }

    @Test
    public void executeShouldPresentSuccess() {
        interactor.execute(requestModel);

        verify(outputBoundary).presentSuccess(responseModel);
    }

    @Test
    public void executeShouldPresentInvalidAttributeWhenTitleIsInvalid() {
        when(task.isTitleValid()).thenReturn(false);

        interactor.execute(requestModel);

        verify(outputBoundary)
                .presentInvalidAttribute("Título de tarefa inválido. Título deve conter pelo menos 3 caracteres");
    }

    @Test
    public void executeShouldPresentInvalidAttributeWhenDescriptionIsInvalid() {
        when(task.isDescriptionValid()).thenReturn(false);

        interactor.execute(requestModel);

        verify(outputBoundary)
                .presentInvalidAttribute(
                        "Descricão de tarefa inválida. Descricão deve conter pelo menos 10 caracteres");
    }

    @Test
    public void executeShouldPresentInvalidAttributeWhenDueDateIsInvalid() {
        when(task.isDueDateValid()).thenReturn(false);

        interactor.execute(requestModel);

        verify(outputBoundary)
                .presentInvalidAttribute("Data de vencimento inválida. Data deve ser posterior a data atual");
    }
}
