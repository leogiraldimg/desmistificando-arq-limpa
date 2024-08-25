package giraldi.dev.application.domain.task.create.models;

import giraldi.dev.entities.domain.task.TaskStatus;

import java.time.LocalDate;
import java.util.Objects;

public class CreateTaskRequestModel {

    public String title;
    public String description;
    public LocalDate dueDate;
    public TaskStatus status;

    public CreateTaskRequestModel(String title, String description, LocalDate dueDate, TaskStatus status) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CreateTaskRequestModel that = (CreateTaskRequestModel) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(dueDate, that.dueDate) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, dueDate, status);
    }
}
