package upc.edu.pe.shared.domain.events;

import java.time.Instant;

public interface DomainEvent {
    Instant occurredAt();
}
