package upc.edu.pe.logistics.domain.model.valueobjects;

public record DeliveryCode(String value) {
    private static final String NOT_BLANK_MESSAGE_KEY = "claim.error.claimIdentifier.notBlank";
    private static final String INVALID_MESSAGE_KEY = "claim.error.claimIdentifier.invalid";

}
