package giraldi.dev.infra.web.spring.controllers.domain.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import giraldi.dev.adapters.domain.task.create.models.web.CreateTaskWebBodyModel;
import giraldi.dev.entities.domain.task.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TaskControllerTestsIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String title;
    private String description;
    private String dueDate;
    private String taskStatus;
    private CreateTaskWebBodyModel createTaskWebBodyModel;
    private Long countTotalTasks;

    @BeforeEach
    public void setUp() {
        title = "Task 1";
        description = "Description 1";
        dueDate = LocalDate.now().plusDays(1).toString();
        taskStatus = TaskStatus.TODO.toString();
        countTotalTasks = 5L;

        createTaskWebBodyModel = new CreateTaskWebBodyModel(title, description, dueDate, taskStatus);
    }

    @Test
    public void insertShouldReturnCreatedTask() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(createTaskWebBodyModel);

        ResultActions result = mockMvc.perform(post("/tasks")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.content.id").value(countTotalTasks + 1));
        result.andExpect(jsonPath("$.content.title").value(title));
        result.andExpect(jsonPath("$.content.description").value(description));
        result.andExpect(jsonPath("$.content.dueDate").value(dueDate));
        result.andExpect(jsonPath("$.content.status").value(taskStatus));
    }

    @Test
    public void insertShouldReturnBadRequestWhenTitleIsNotGiven() throws Exception {
        createTaskWebBodyModel.title = null;
        String jsonBody = objectMapper.writeValueAsString(createTaskWebBodyModel);

        ResultActions result = mockMvc.perform(post("/tasks")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.message").value("Título de tarefa é obrigatório"));
    }

    @Test
    public void insertShouldReturnBadRequestWhenTitleIsLessThanThreeCharacters() throws Exception {
        createTaskWebBodyModel.title = "a".repeat(2);
        String jsonBody = objectMapper.writeValueAsString(createTaskWebBodyModel);

        ResultActions result = mockMvc.perform(post("/tasks")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
        result.andExpect(
                jsonPath("$.message").value("Título de tarefa inválido. Título deve conter pelo menos 3 caracteres"));
    }

    @Test
    public void insertShouldReturnBadRequestWhenDescriptionIsNotGiven() throws Exception {
        createTaskWebBodyModel.description = null;
        String jsonBody = objectMapper.writeValueAsString(createTaskWebBodyModel);

        ResultActions result = mockMvc.perform(post("/tasks")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.message").value("Descricão de tarefa é obrigatório"));
    }

    @Test
    public void insertShouldReturnBadRequestWhenDescriptionIsLessThanTenCharacters() throws Exception {
        createTaskWebBodyModel.description = "a".repeat(9);
        String jsonBody = objectMapper.writeValueAsString(createTaskWebBodyModel);

        ResultActions result = mockMvc.perform(post("/tasks")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
        result.andExpect(
                jsonPath("$.message")
                        .value("Descricão de tarefa inválida. Descricão deve conter pelo menos 10 caracteres"));
    }

    @Test
    public void insertShouldReturnBadRequestWhenDueDateIsNotGiven() throws Exception {
        createTaskWebBodyModel.dueDate = null;
        String jsonBody = objectMapper.writeValueAsString(createTaskWebBodyModel);

        ResultActions result = mockMvc.perform(post("/tasks")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.message").value("Data de vencimento é obrigatório"));
    }

    @Test
    public void insertShouldReturnBadRequestWhenDueDateHasInvalidFormat() throws Exception {
        createTaskWebBodyModel.dueDate = "invalid";
        String jsonBody = objectMapper.writeValueAsString(createTaskWebBodyModel);

        ResultActions result = mockMvc.perform(post("/tasks")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.message").value(
                "Data de vencimento deve estar no formato \"YYYY-mm-dd\""));
    }

    @Test
    public void insertShouldReturnBadRequestWhenDueDateIsInThePast() throws Exception {
        createTaskWebBodyModel.dueDate = LocalDate.now().minusDays(1).toString();
        String jsonBody = objectMapper.writeValueAsString(createTaskWebBodyModel);

        ResultActions result = mockMvc.perform(post("/tasks")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
        result.andExpect(
                jsonPath("$.message").value("Data de vencimento inválida. Data deve ser posterior a data atual"));
    }

    @Test
    public void insertShouldReturnBadRequestWhenStatusIsNotGiven() throws Exception {
        createTaskWebBodyModel.status = null;
        String jsonBody = objectMapper.writeValueAsString(createTaskWebBodyModel);

        ResultActions result = mockMvc.perform(post("/tasks")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.message").value("Status de tarefa é obrigatório"));
    }

    @Test
    public void insertShouldReturnBadRequestWhenStatusIsInvalid() throws Exception {
        createTaskWebBodyModel.status = "invalid";
        String jsonBody = objectMapper.writeValueAsString(createTaskWebBodyModel);

        ResultActions result = mockMvc.perform(post("/tasks")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.message").value("Status de tarefa deve ser \"TODO\", \"IN_PROGRESS\" ou \"DONE\""));
    }
}
