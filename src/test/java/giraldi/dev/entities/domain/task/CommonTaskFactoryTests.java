package giraldi.dev.entities.domain.task;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommonTaskFactoryTests {
    CommonTaskFactory commonTaskFactory;

    CommonTask task;

    @BeforeEach
    void setUp() {
        commonTaskFactory = new CommonTaskFactory();

        task = new CommonTask("Task 1", "Description 1", LocalDate.now().plusDays(1), TaskStatus.TODO);
    }

    @Test
    public void createShouldReturnCommonTask() {
        Task result = commonTaskFactory.create("Task 1", "Description 1", LocalDate.now().plusDays(1), TaskStatus.TODO);

        assertEquals(task.getTitle(), result.getTitle());
        assertEquals(task.getDescription(), result.getDescription());
        assertEquals(task.getDueDate(), result.getDueDate());
        assertEquals(task.getStatus(), result.getStatus());
    }
}
