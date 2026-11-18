package upc.edu.pe.logistics.domain.events;

import upc.edu.pe.shared.domain.events.DomainEvent;

import java.time.Instant;

public record OrderCreatedEvent(String deliveryIdentifier, Instant ocurredAt) implements DomainEvent {
    @Override
    public Instant occurredAt() {
        return null;
    }
}
