package giraldi.dev.infra.datasource.jpa.domain.task.create;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import giraldi.dev.application.domain.task.create.models.CreateTaskDsRequestModel;
import giraldi.dev.application.domain.task.create.models.CreateTaskDsResponseModel;
import giraldi.dev.entities.domain.task.TaskStatus;
import giraldi.dev.infra.datasource.jpa.domain.task.TaskDataMapper;
import giraldi.dev.infra.datasource.jpa.domain.task.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
public class CreateTasksDsJPATests {

    @InjectMocks
    private CreateTaskDsJPA createTaskDsJPA;

    @Mock
    private TaskRepository repository;

    private TaskDataMapper dataMapper;
    private CreateTaskDsRequestModel requestModel;
    private CreateTaskDsResponseModel responseModel;

    @BeforeEach
    public void setUp() {
        requestModel = new CreateTaskDsRequestModel(
                "Task 1",
                "Description task 1",
                LocalDate.now(),
                TaskStatus.TODO);
        dataMapper = new TaskDataMapper(
                1L,
                requestModel.title,
                requestModel.description,
                requestModel.dueDate,
                requestModel.status);
        responseModel = new CreateTaskDsResponseModel(
                dataMapper.getId().toString(),
                requestModel.title,
                requestModel.description,
                requestModel.dueDate,
                requestModel.status);

        when(repository.save(ArgumentMatchers.any())).thenReturn(dataMapper);
    }

    @Test
    public void saveShouldReturnResponseModel() {
        CreateTaskDsResponseModel result = createTaskDsJPA.save(requestModel);

        assertEquals(responseModel, result);
    }
}
