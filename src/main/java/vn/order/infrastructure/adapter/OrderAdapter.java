package vn.order.infrastructure.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.order.domain.enums.OrderStatusEnum;
import vn.order.domain.model.Order;
import vn.order.domain.model.OrderQueue;
import vn.order.domain.ports.OrderPort;
import vn.order.infrastructure.config.Mapper;
import vn.order.infrastructure.entities.OrderEntity;
import vn.order.infrastructure.repository.OrderQueueRepository;
import vn.order.infrastructure.repository.OrderRepository;

import java.time.LocalDateTime;

@Component
public class OrderAdapter implements OrderPort {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderQueueRepository orderQueueRepository;
    @Override
    public Order createOrder(Order order, OrderQueue orderQueue) {
        OrderEntity orderEntity = OrderEntity.builder()
                .customerId(order.getCustomerId())
                .menuIDS(Mapper.writeValueAsString(order.getMenuIds()))
                .status(OrderStatusEnum.CREATED.name())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .orderQueue(orderQueue!=null ? orderQueueRepository.findById(orderQueue.getId()).get(): null)
                .shopId(order.getShopId()).build();
        orderEntity =  this.orderRepository.save(orderEntity);
        return Order.builder()
                .id(orderEntity.getId())
                .customerId(orderEntity.getCustomerId())
                .menuIds(order.getMenuIds())
                .shopId(orderEntity.getShopId())
                .status(OrderStatusEnum.valueOf(orderEntity.getStatus()))
                .build();
    }
}
