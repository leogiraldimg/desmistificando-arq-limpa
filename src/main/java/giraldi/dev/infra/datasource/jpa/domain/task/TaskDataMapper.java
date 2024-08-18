package giraldi.dev.infra.datasource.jpa.domain.task;

import java.time.Instant;
import java.time.LocalDate;

import giraldi.dev.entities.domain.task.TaskStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "tasks")
@NoArgsConstructor
public class TaskDataMapper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Long id;

    @Column(name = "title", columnDefinition = "TEXT", nullable = false)
    @Setter
    private String title;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    @Setter
    private String description;

    @Column(name = "dueDate", columnDefinition = "DATE", nullable = false)
    @Setter
    private LocalDate dueDate;

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    @Setter
    private TaskStatus status;

    @Column(name = "createdAt", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;

    @Column(name = "updatedAt", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }

    public TaskDataMapper(Long id, String title, String description, LocalDate dueDate, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }

    public TaskDataMapper(String title, String description, LocalDate dueDate, TaskStatus status) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }
}
