package giraldi.dev.infra.web.spring.handlers;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import giraldi.dev.adapters.common.models.web.WebViewModel;
import giraldi.dev.infra.web.spring.controllers.common.SpringWebHandler;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ApplicationContext applicationContext;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleValidationException(HttpServletResponse response, MethodArgumentNotValidException ex) {
        SpringWebHandler<Void> webHandler = applicationContext.getBean(SpringWebHandler.class, response);
        String errorMessage = ex.getBindingResult().getAllErrors().getFirst().getDefaultMessage();
        webHandler.send(new WebViewModel<Void>(400, true, errorMessage, null, Instant.now()));
    }
}
