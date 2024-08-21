package giraldi.dev.adapters.common.models.web;

import java.time.Instant;

public class WebViewModel<T> {
    public int statusCode;
    public Boolean error;
    public String message;
    public T content;
    public Instant timestamp;

    public WebViewModel(int statusCode, Boolean error, String message, T content, Instant timestamp) {
        this.statusCode = statusCode;
        this.error = error;
        this.message = message;
        this.content = content;
        this.timestamp = timestamp;
    }
}
