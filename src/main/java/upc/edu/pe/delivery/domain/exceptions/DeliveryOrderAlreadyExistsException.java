package upc.edu.pe.delivery.domain.exceptions;

public class DeliveryOrderAlreadyExistsException extends RuntimeException {
    public DeliveryOrderAlreadyExistsException(String message) {
        super(message);
    }
}
