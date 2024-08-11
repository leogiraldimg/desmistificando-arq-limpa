package giraldi.dev.entities.domain.task;

import java.time.LocalDate;

public class CommonTaskFactory extends TaskFactory {

    @Override
    public Task create(String title, String description, LocalDate dueDate, TaskStatus status) {
        return new CommonTask(title, description, dueDate, status);
    }
}
