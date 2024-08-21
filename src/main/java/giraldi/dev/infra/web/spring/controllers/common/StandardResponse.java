package giraldi.dev.infra.web.spring.controllers.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StandardResponse<T> {
    
    private int statusCode;
    private boolean error;
    private String message;
    private T content;
}
