package upc.edu.pe.logistics.domain.repositories;

import upc.edu.pe.logistics.domain.model.aggregates.Order;

public interface DeliveryRepository {

    Order Save(Order order);
}
