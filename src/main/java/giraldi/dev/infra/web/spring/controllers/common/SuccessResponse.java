package giraldi.dev.infra.web.spring.controllers.common;

public class SuccessResponse<T> extends StandardResponse<T> {

    public SuccessResponse(int statusCode, String message, T content) {
        super(statusCode, false, message, content);
    }
}
