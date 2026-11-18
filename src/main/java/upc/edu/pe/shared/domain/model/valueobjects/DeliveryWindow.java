package upc.edu.pe.shared.domain.model.valueobjects;

import java.time.LocalDate;

public record DeliveryWindow(LocalDate startDate, LocalDate enDate) {
    private static final String MISSING_MESSAGE_KEY = "claim.error.deliveryWindow.missing";
    private static final String ORDER_MESSAGE_KEY = "claim.error.deliveryWindow.invalid";

    public DeliveryWindow {
        if (startDate == null || enDate == null) {
            throw new IllegalArgumentException(MISSING_MESSAGE_KEY);
        }
        if (!enDate.isAfter(startDate)) {
            throw new IllegalArgumentException(ORDER_MESSAGE_KEY);
        }
    }

    public boolean isExpired(LocalDate checkDate) {
        return checkDate != null && checkDate.isAfter(enDate);
    }
}
