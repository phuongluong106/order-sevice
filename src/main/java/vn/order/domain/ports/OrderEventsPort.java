package vn.order.domain.ports;

import vn.order.domain.model.Order;
import vn.order.domain.model.OrderQueue;

public interface OrderEventsPort {
    boolean createOrderEvent(Order order , OrderQueue orderQueue);
}
