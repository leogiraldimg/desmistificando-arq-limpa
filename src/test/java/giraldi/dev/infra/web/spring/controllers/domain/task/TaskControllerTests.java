package giraldi.dev.infra.web.spring.controllers.domain.task;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import giraldi.dev.adapters.common.models.web.WebRequestModel;
import giraldi.dev.adapters.domain.task.create.controllers.web.CreateTaskWebController;
import giraldi.dev.adapters.domain.task.create.models.web.CreateTaskWebBodyModel;
import giraldi.dev.entities.domain.task.TaskStatus;

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
    private LocalDate dueDate;
    private TaskStatus taskStatus;
    private CreateTaskWebBodyModel createTaskWebBodyModel;

    @BeforeEach
    public void setUp() {
        createTaskWebBodyModel = new CreateTaskWebBodyModel(title, description, dueDate, taskStatus);
    }

    @Test
    public void insertShouldCallCreateTaskWebController() throws Exception {
        WebRequestModel<Void, Void, CreateTaskWebBodyModel, Void> expected = new WebRequestModel<Void, Void, CreateTaskWebBodyModel, Void>();
        expected.body = createTaskWebBodyModel;
        String jsonBody = objectMapper.writeValueAsString(createTaskWebBodyModel);

        mockMvc.perform(post("/tasks")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        verify(createTaskWebController).execute(expected);
    }
}
