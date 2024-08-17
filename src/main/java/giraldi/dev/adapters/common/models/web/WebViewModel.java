package giraldi.dev.adapters.common.models.web;

import java.time.LocalDateTime;

public class WebViewModel<T> {
    public int statusCode;
    public Boolean error;
    public String message;
    public T content;
    public LocalDateTime timestamp;

    public WebViewModel(int statusCode, Boolean error, String message, T content, LocalDateTime timestamp) {
        this.statusCode = statusCode;
        this.error = error;
        this.message = message;
        this.content = content;
        this.timestamp = timestamp;
    }
}
