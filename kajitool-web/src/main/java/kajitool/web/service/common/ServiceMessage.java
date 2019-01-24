package kajitool.web.service.common;

import java.util.List;

public class ServiceMessage {
    private final String code;
    private final String message;
    private final List<?> details;

    public ServiceMessage(final String code,
                          final String message) {
        this.code = code;
        this.message = message;
        this.details = null;
    }
    public ServiceMessage(final String code,
                          final String message,
                          final List<?> details) {
        this.code = code;
        this.message = message;
        this.details = details;
    }
    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public List<?> getDetails() {
        return details;
    }
}