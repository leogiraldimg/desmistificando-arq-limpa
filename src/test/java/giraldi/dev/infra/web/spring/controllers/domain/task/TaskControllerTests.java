package giraldi.dev.infra.web.spring.controllers.domain.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import giraldi.dev.adapters.common.models.web.WebRequestModel;
import giraldi.dev.adapters.domain.task.create.controllers.web.CreateTaskWebController;
import giraldi.dev.adapters.domain.task.create.models.web.CreateTaskWebBodyModel;
import giraldi.dev.entities.domain.task.TaskStatus;
import giraldi.dev.infra.web.spring.controllers.domain.task.models.CreateTaskBodyModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(TaskController.class)
public class TaskControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateTaskWebController createTaskWebController;

    @Autowired
    private ObjectMapper objectMapper;

    private String title;
    private String description;
    private String dueDate;
    private String taskStatus;
    private CreateTaskBodyModel createTaskBodyModel;
    private CreateTaskWebBodyModel createTaskWebBodyModel;
    private WebRequestModel<Void, Void, CreateTaskWebBodyModel, Void> webRequestModel;

    @BeforeEach
    public void setUp() {
        title = "Task 1";
        description = "Description 1";
        dueDate = LocalDate.now().plusDays(1).toString();
        taskStatus = TaskStatus.TODO.toString();

        webRequestModel = new WebRequestModel<Void, Void, CreateTaskWebBodyModel, Void>();
        createTaskBodyModel = new CreateTaskBodyModel(title, description, dueDate, taskStatus);
        createTaskWebBodyModel = new CreateTaskWebBodyModel(title, description, dueDate, taskStatus);
    }

    @Test
    public void insertShouldCallCreateTaskWebController() throws Exception {
        webRequestModel.body = createTaskWebBodyModel;
        String jsonBody = objectMapper.writeValueAsString(createTaskBodyModel);

        mockMvc.perform(post("/tasks")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        verify(createTaskWebController).execute(webRequestModel);
    }
}
