package giraldi.dev.entities.domain.task;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommonTaskTests {

    private CommonTask task;

    private String title;
    private String description;
    private LocalDate dueDate;
    private TaskStatus taskStatus;

    @BeforeEach
    void setUp() {
        title = "Task 1";
        description = "Description 1";
        dueDate = LocalDate.now().plusDays(1);
        taskStatus = TaskStatus.TODO;

        task = new CommonTask(title, description, dueDate, taskStatus);
    }

    @Test
    public void isTitleValidShouldReturnTrue() {
        Boolean result = task.isTitleValid();

        assertTrue(result);
    }

    @Test
    public void isTitleValidShouldReturnFalseWhenTitleIsSmallerThan3Characters() {
        task = new CommonTask("a".repeat(2), description, dueDate, taskStatus);

        Boolean result = task.isTitleValid();

        assertFalse(result);
    }

    @Test
    public void isDescriptionValidShouldReturnTrue() {
        Boolean result = task.isDescriptionValid();

        assertTrue(result);
    }

    @Test
    public void isDescriptionValidShouldReturnFalseWhenDescriptionIsSmallerThan10Characters() {
        task = new CommonTask(title, "a".repeat(9), dueDate, taskStatus);

        Boolean result = task.isDescriptionValid();

        assertFalse(result);
    }

    @Test
    public void isDueDateValidShouldReturnTrue() {
        Boolean result = task.isDueDateValid();

        assertTrue(result);
    }

    @Test
    public void isDueDateValidShouldReturnFalseWhenDueDateIsADateInPast() {
        task = new CommonTask(title, description, LocalDate.of(2020, 1, 1), taskStatus);

        Boolean result = task.isDueDateValid();

        assertFalse(result);
    }

    @Test
    public void getTitleShouldReturnTitle() {
        String result = task.getTitle();

        assertEquals(title, result);
    }

    @Test
    public void getDescriptionShouldReturnDescription() {
        String result = task.getDescription();

        assertEquals(description, result);
    }

    @Test
    public void getDueDateShouldReturnDueDate() {
        LocalDate result = task.getDueDate();

        assertEquals(dueDate, result);
    }

    @Test
    public void getStatusShouldReturnStatus() {
        TaskStatus result = task.getStatus();

        assertEquals(taskStatus, result);
    }
}
