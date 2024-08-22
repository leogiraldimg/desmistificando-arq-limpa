package giraldi.dev.adapters.domain.task.create.models.web;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CreateTaskWebBodyModel that = (CreateTaskWebBodyModel) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(dueDate, that.dueDate) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, dueDate, status);
    }
}
