package vn.order.infrastructure.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.order.domain.enums.OrderStatusEnum;
import vn.order.domain.model.Order;
import vn.order.domain.model.OrderQueue;
import vn.order.domain.ports.OrderEventsPort;
import vn.order.infrastructure.entities.OrderEventsEntity;
import vn.order.infrastructure.repository.OrderEventsRepository;
import vn.order.infrastructure.repository.OrderRepository;

import java.time.LocalDateTime;

@Component
public class OrderEventsAdapter implements OrderEventsPort {
    @Autowired
    OrderEventsRepository orderEventsRepository;
    @Autowired
    OrderRepository orderRepository;

    @Override
    public boolean createOrderEvent(Order order, OrderQueue orderQueue) {
            this.orderEventsRepository.save(OrderEventsEntity
                    .builder()
                    .order(orderRepository.findById(order.getId()).get())
                    .createdDate(LocalDateTime.now())
                    .customerId(order.getCustomerId())
                    .shopId(order.getShopId())
                    .eventName(order.getStatus().name())
                    .status(order.getStatus().name())
                    .updatedDate(LocalDateTime.now()).build());
            return true;
    }
}
