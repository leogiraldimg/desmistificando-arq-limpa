package giraldi.dev.entities.domain.task;

import java.time.LocalDate;

public class CommonTask implements Task {
    private String title;
    private String description;
    private LocalDate dueDate;
    private TaskStatus status;

    public CommonTask(String title, String description, LocalDate dueDate, TaskStatus status) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }

    public boolean isTitleValid() {
        return title.length() > 2;
    }

    public boolean isDescriptionValid() {
        return description.length() > 9;
    }

    public boolean isDueDateValid() {
        return LocalDate.now().isBefore(dueDate);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskStatus getStatus() {
        return status;
    }
}
