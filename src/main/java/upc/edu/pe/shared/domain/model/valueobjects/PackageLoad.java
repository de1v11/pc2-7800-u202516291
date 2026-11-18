package upc.edu.pe.shared.domain.model.valueobjects;

import java.math.BigDecimal;

public record PackageLoad(BigDecimal value, String currency) {
    private static final String VALUE_MESSAGE_KEY = "claim.error.PackedLoad.value.invalid";
    private static final String CURRENCY_MESSAGE_KEY = "claim.error.PackedLoad.currency.invalid";

    public PackageLoad{
        if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(VALUE_MESSAGE_KEY);
        }
        if (currency == null || currency.isBlank()) {
            throw new IllegalArgumentException(CURRENCY_MESSAGE_KEY);
        }
    }

    public PackageLoad multiplyBy(BigDecimal factor) {
        if (factor == null) {
            throw new IllegalArgumentException(VALUE_MESSAGE_KEY);
        }
        return new PackageLoad(value.multiply(factor), currency);
    }

}
