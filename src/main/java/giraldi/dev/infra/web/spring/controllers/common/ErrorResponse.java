package giraldi.dev.infra.web.spring.controllers.common;

public class ErrorResponse<T> extends StandardResponse<T> {

    public ErrorResponse(int statusCode, String message, T content) {
        super(statusCode, true, message, content);
    }
}
