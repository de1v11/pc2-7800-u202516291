package upc.edu.pe.logistics.domain.model.aggregates;

import upc.edu.pe.logistics.domain.events.OrderCreatedEvent;
import upc.edu.pe.logistics.domain.model.valueobjects.DeliveryCode;
import upc.edu.pe.logistics.domain.model.valueobjects.DeliveryStatus;
import upc.edu.pe.logistics.domain.model.valueobjects.WarehouseId;
import upc.edu.pe.shared.domain.aggregates.AbstractDomainAggregateRoot;
import upc.edu.pe.shared.domain.model.valueobjects.DeliveryWindow;
import upc.edu.pe.shared.domain.model.valueobjects.PackageLoad;

import java.time.Instant;
import java.util.Objects;

public final class Order extends AbstractDomainAggregateRoot {
    private final Long id;
    private final DeliveryCode deliveryCode;
    private final WarehouseId warehouseId;
    private final DeliveryWindow deliveryWindow;
    private final PackageLoad packageLoad;
    private final DeliveryStatus status;
    private final Integer priority;
    private final String notes;
    private final Instant createdAt;
    private final Instant updatedAt;

    public Order(Long id, DeliveryCode deliveryCode, WarehouseId warehouseId, DeliveryWindow deliveryWindow, PackageLoad packageLoad, DeliveryStatus status, Integer priority, String notes, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.deliveryCode = Objects.requireNonNull(deliveryCode, "delivery code must not be null");
        this.warehouseId = Objects.requireNonNull(warehouseId, "delivery code must not be null");;
        this.deliveryWindow = Objects.requireNonNull(deliveryWindow, "delivery code must not be null");;
        this.packageLoad = Objects.requireNonNull(packageLoad, "delivery code must not be null");;
        this.status = status;
        this.priority = priority;
        this.notes = notes;
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(createdAt, "updateAt must not be null");
    }

    public static Order create(DeliveryCode deliveryCode, WarehouseId warehouseId,
                               DeliveryWindow deliveryWindow, PackageLoad packageLoad,
                               DeliveryStatus status, Integer priority, String notes, Instant now) {
        var effectiveStatus = status == null ? DeliveryStatus.PENDING : status;
        var order = new Order(null, deliveryCode, warehouseId, deliveryWindow,
                packageLoad, effectiveStatus, priority,notes , now, now);
        order.registerEvent(new OrderCreatedEvent(deliveryCode.value(), now));
        return order;
    }

    public Long getId() {
        return id;
    }

    public DeliveryCode getDeliveryCode() {
        return deliveryCode;
    }

    public WarehouseId getWarehouseId() {
        return warehouseId;
    }

    public DeliveryWindow getDeliveryWindow() {
        return deliveryWindow;
    }

    public PackageLoad getPackageLoad() {
        return packageLoad;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public Integer getPriority() {
        return priority;
    }

    public String getNotes() {
        return notes;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
