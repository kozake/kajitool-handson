package kajitool.web.controller.error;

import kajitool.web.service.common.ServiceException;
import kajitool.web.service.common.ServiceMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Object> handleAppServiceException(
            final ServiceException ex, final WebRequest request) {
        return super.handleExceptionInternal(
                ex,
                ex.getServiceMessage(),
                null,
                HttpStatus.BAD_REQUEST,
                request);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(
            final Exception ex, final WebRequest request) {
        return super.handleExceptionInternal(
                ex,
                new ServiceMessage("fatal", "Internal error."),
                null,
                HttpStatus.INTERNAL_SERVER_ERROR,
                request);
    }
}
