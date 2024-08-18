package giraldi.dev.application.domain.task.create.models;

import java.time.LocalDate;
import java.util.Objects;

import giraldi.dev.entities.domain.task.TaskStatus;

public class CreateTaskDsResponseModel {

    public String id;
    public String title;
    public String description;
    public LocalDate dueDate;
    public TaskStatus status;

    public CreateTaskDsResponseModel(String id, String title, String description, LocalDate dueDate,
            TaskStatus status) {
        this.id = id;
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
        CreateTaskDsResponseModel that = (CreateTaskDsResponseModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(dueDate, that.dueDate) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, dueDate, status);
    }
}
