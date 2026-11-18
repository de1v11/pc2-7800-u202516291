package upc.edu.pe.logistics.domain.exceptions;

public class DeliveryOrderAlreadyExistsException extends RuntimeException {
    public DeliveryOrderAlreadyExistsException(String message) {
        super(message);
    }
}
