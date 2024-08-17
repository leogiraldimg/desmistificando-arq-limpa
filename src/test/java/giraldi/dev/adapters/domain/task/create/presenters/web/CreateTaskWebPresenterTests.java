package giraldi.dev.adapters.domain.task.create.presenters.web;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import giraldi.dev.adapters.common.handlers.WebHandler;
import giraldi.dev.application.domain.task.create.models.CreateTaskResponseModel;
import giraldi.dev.entities.domain.task.TaskStatus;

@ExtendWith(MockitoExtension.class)
public class CreateTaskWebPresenterTests {

    @InjectMocks
    private CreateTaskWebPresenter presenter;

    @Mock
    private WebHandler<CreateTaskResponseModel> handler;

    @Test
    public void presentSuccessShouldSendViewModel() {
        CreateTaskResponseModel responseModel = new CreateTaskResponseModel(
                "1",
                "Task 1",
                "Task description 1",
                LocalDate.now().plusDays(1).toString(),
                TaskStatus.TODO);

        presenter.presentSuccess(responseModel);

        verify(handler).send(argThat(viewModel -> viewModel.content.equals(responseModel) && viewModel.statusCode == 200
                && viewModel.error == false && viewModel.message.equals("Tarefa cadastrada com sucesso")
                && viewModel.timestamp != null));
    }

    @Test
    public void presentInvalidAttributeShouldSendViewModel() {
        String error = "Invalid attribute";

        presenter.presentInvalidAttribute(error);

        verify(handler).send(argThat(viewModel -> viewModel.content == null && viewModel.statusCode == 400
                && viewModel.error == true && viewModel.message.equals(error)
                && viewModel.timestamp != null));
    }
}
