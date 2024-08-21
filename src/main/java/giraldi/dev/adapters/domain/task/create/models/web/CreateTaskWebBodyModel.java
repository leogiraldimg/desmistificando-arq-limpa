package giraldi.dev.adapters.domain.task.create.models.web;

import java.time.LocalDate;

import giraldi.dev.entities.domain.task.TaskStatus;

public class CreateTaskWebBodyModel {

    public String title;
    public String description;
    public LocalDate dueDate;
    public TaskStatus status;

    public CreateTaskWebBodyModel(String title, String description, LocalDate dueDate, TaskStatus status) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }

    public CreateTaskWebBodyModel() {
    }
}
