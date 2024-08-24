package giraldi.dev.infra.web.spring.controllers.common;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import giraldi.dev.adapters.common.handlers.web.WebHandler;
import giraldi.dev.adapters.common.models.web.WebViewModel;
import jakarta.servlet.http.HttpServletResponse;

public class SpringWebHandler<T> implements WebHandler<T> {

    private HttpServletResponse httpResponse;

    public SpringWebHandler(HttpServletResponse response) {
        this.httpResponse = response;
    }

    @Override
    public void send(WebViewModel<T> viewModel) {
        httpResponse.setStatus(viewModel.statusCode);

        httpResponse.setContentType(MediaType.APPLICATION_JSON.toString());
        httpResponse.setCharacterEncoding("UTF-8");

        StandardResponse<T> response = null;

        if (viewModel.error) {
            response = new ErrorResponse<T>(
                    viewModel.statusCode,
                    viewModel.message,
                    viewModel.content);
        } else {
            response = new SuccessResponse<T>(
                    viewModel.statusCode,
                    viewModel.message,
                    viewModel.content);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String body = null;
        try {
            body = objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        try (PrintWriter writer = httpResponse.getWriter()) {
            writer.write(body);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
