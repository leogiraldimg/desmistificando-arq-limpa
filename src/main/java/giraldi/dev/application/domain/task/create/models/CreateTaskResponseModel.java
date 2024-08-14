package giraldi.dev.application.domain.task.create.models;

import java.util.Objects;

import giraldi.dev.entities.domain.task.TaskStatus;

public class CreateTaskResponseModel {

    public String id;
    public String title;
    public String description;
    public String dueDate;
    public TaskStatus status;

    public CreateTaskResponseModel(String id, String title, String description, String dueDate, TaskStatus status) {
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
        CreateTaskResponseModel that = (CreateTaskResponseModel) o;
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
