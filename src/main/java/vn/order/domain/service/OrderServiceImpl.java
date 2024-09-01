package vn.order.domain.service;

import org.springframework.transaction.annotation.Transactional;
import vn.order.domain.enums.OrderStatusEnum;
import vn.order.domain.model.Order;
import vn.order.domain.model.OrderQueue;
import vn.order.domain.ports.OrderEventsPort;
import vn.order.domain.ports.OrderPort;
import vn.order.domain.ports.OrderQueuePort;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl extends AbstractService implements OrderService {
    private OrderPort orderPort;
    private OrderEventsPort orderEventsPort;
    private OrderQueuePort orderQueuePort;

    public OrderServiceImpl(OrderPort orderPort, OrderEventsPort orderEventsPort, OrderQueuePort orderQueuePort) {
        this.orderPort = orderPort;
        this.orderEventsPort = orderEventsPort;
        this.orderQueuePort = orderQueuePort;
    }

    @Override
    public Order createOrder(Order order) {
        List< OrderQueue> orderQueueList = this.orderQueuePort.getOrderQueueByShopId(order.getShopId());
        Optional<OrderQueue> orderQueue =orderQueueList.stream()
                .filter(queue->queue.getOrderInQueue() < queue.getQueueSize()).sorted((queue1, queue2)-> queue2.getOrderInQueue().compareTo(queue1.getOrderInQueue()))
                .findFirst();
        if(orderQueue.isPresent()){
            order = this.orderPort.createOrder(order, orderQueue.get());
            this.orderEventsPort.createOrderEvent(order, orderQueue.get());
            orderQueue.get().setOrderInQueue(orderQueue.get().getOrderInQueue() + 1);
            order.setStatus(OrderStatusEnum.IN_QUEUE);
            order = this.orderPort.createOrder(order, orderQueue.get());
            this.orderEventsPort.createOrderEvent(order, orderQueue.get());
            this.orderQueuePort.saveOrderQueue(orderQueue.get());
            return order;
        }
        else{
            order = this.orderPort.createOrder(order, null);
            this.orderEventsPort.createOrderEvent(order, orderQueue.get());
            return order;
        }
    }
}
