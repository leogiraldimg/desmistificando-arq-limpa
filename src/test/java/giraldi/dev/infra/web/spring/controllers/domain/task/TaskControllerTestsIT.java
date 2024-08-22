package giraldi.dev.infra.web.spring.controllers.domain.task;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import giraldi.dev.adapters.common.models.web.WebRequestModel;
import giraldi.dev.adapters.domain.task.create.models.web.CreateTaskWebBodyModel;
import giraldi.dev.entities.domain.task.TaskStatus;

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
    private LocalDate dueDate;
    private TaskStatus taskStatus;
    private CreateTaskWebBodyModel createTaskWebBodyModel;
    private Long countTotalTasks;

    @BeforeEach
    public void setUp() {
        title = "Task 1";
        description = "Description 1";
        dueDate = LocalDate.now().plusDays(1);
        taskStatus = TaskStatus.TODO;
        countTotalTasks = 5L;

        createTaskWebBodyModel = new CreateTaskWebBodyModel(title, description, dueDate, taskStatus);
    }

    @Test
    public void insertShouldReturnCreatedTask() throws Exception {
        WebRequestModel<Void, Void, CreateTaskWebBodyModel, Void> expected = new WebRequestModel<Void, Void, CreateTaskWebBodyModel, Void>();
        expected.body = createTaskWebBodyModel;
        String jsonBody = objectMapper.writeValueAsString(createTaskWebBodyModel);

        ResultActions result = mockMvc.perform(post("/tasks")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.content.id").value(countTotalTasks + 1));
        result.andExpect(jsonPath("$.content.title").value(title));
        result.andExpect(jsonPath("$.content.description").value(description));
        result.andExpect(jsonPath("$.content.dueDate").value(dueDate.toString()));
        result.andExpect(jsonPath("$.content.status").value(taskStatus.toString()));
    }
}
