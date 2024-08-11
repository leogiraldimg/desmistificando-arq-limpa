package giraldi.dev.entities.domain.task;

import java.time.LocalDate;

public abstract class TaskFactory {
    public abstract Task create(String title, String description, LocalDate dueDate, TaskStatus status);
}
