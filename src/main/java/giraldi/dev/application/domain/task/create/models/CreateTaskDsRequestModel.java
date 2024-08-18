package giraldi.dev.application.domain.task.create.models;

import java.time.LocalDate;

import giraldi.dev.entities.domain.task.TaskStatus;

public class CreateTaskDsRequestModel {

    public String title;
    public String description;
    public LocalDate dueDate;
    public TaskStatus status;

    public CreateTaskDsRequestModel(String title, String description, LocalDate dueDate, TaskStatus status) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }
}
