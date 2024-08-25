package giraldi.dev.infra.web.spring.controllers.domain.task.models;

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
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}$", message = "Data de vencimento deve estar no formato \"YYYY-mm-dd\"")
    public String dueDate;

    @NotNull(message = "Status de tarefa é obrigatório")
    @Pattern(regexp = "TODO|IN_PROGRESS|DONE", message = "Status de tarefa deve ser \"TODO\", \"IN_PROGRESS\" ou \"DONE\"")
    public String status;
}
