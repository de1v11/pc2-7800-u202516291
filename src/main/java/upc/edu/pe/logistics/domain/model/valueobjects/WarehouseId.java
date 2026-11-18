package upc.edu.pe.logistics.domain.model.valueobjects;

import java.util.UUID;

public record WarehouseId(String value) {
    private static final String NOT_BLANK_MESSAGE_KEY = "claim.error.policyId.notBlank";
    private static final String INVALID_MESSAGE_KEY = "claim.error.policyId.invalid";

    public WarehouseId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(NOT_BLANK_MESSAGE_KEY);
        }
        try {
            UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(INVALID_MESSAGE_KEY);
        }
    }
}
