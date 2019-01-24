package kajitool.web.service.common;

public class ServiceException extends RuntimeException {

    private final ServiceMessage serviceMessage;

    public ServiceException(
            final ServiceMessage serviceMessage) {
        this.serviceMessage = serviceMessage;
    }
    public ServiceException(
            final ServiceMessage serviceMessage,
            final Throwable cause) {
        this.serviceMessage = serviceMessage;
    }
    public ServiceMessage getServiceMessage() {
        return serviceMessage;
    }
}
