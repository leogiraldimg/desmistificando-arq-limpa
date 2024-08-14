package giraldi.dev.application.domain.task.create.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import giraldi.dev.entities.domain.task.TaskStatus;

public class CreateTaskDsRequestModel {

    public String title;
    public String description;
    public LocalDate dueDate;
    public TaskStatus status;
    public LocalDateTime createdAt;

    public CreateTaskDsRequestModel(String title, String description, LocalDate dueDate, TaskStatus status,
            LocalDateTime createdAt) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.createdAt = createdAt;
    }
}
