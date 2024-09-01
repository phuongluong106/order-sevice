package vn.order.domain.ports;

import vn.order.domain.model.OrderQueue;

import java.util.List;

public interface OrderQueuePort {
    List<OrderQueue> getOrderQueueByShopId(Integer shopId);
    void saveOrderQueue(OrderQueue orderQueue);
}
