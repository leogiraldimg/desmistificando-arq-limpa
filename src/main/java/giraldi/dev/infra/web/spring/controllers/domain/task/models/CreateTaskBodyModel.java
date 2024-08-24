package giraldi.dev.infra.web.spring.controllers.domain.task.models;

import java.time.LocalDate;

import giraldi.dev.entities.domain.task.TaskStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskBodyModel {

    @NotNull(message = "Título de tarefa é obrigatório")
    public String title;

    @NotNull(message = "Descricão de tarefa é obrigatório")
    public String description;

    @NotNull(message = "Data de vencimento é obrigatório")
    public LocalDate dueDate;

    @NotNull(message = "Status de tarefa é obrigatório")
    public TaskStatus status;
}
