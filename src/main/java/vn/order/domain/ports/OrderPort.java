package vn.order.domain.ports;

import com.fasterxml.jackson.core.JsonProcessingException;
import vn.order.domain.model.Order;
import vn.order.domain.model.OrderQueue;

public interface OrderPort {
    Order createOrder(Order order, OrderQueue orderQueue) ;
}
