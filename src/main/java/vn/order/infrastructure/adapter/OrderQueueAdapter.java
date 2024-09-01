package vn.order.infrastructure.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import vn.order.domain.model.OrderQueue;
import vn.order.domain.ports.OrderQueuePort;
import vn.order.infrastructure.entities.OrderQueueEntity;
import vn.order.infrastructure.repository.OrderQueueRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderQueueAdapter implements OrderQueuePort {
    @Autowired
    OrderQueueRepository orderQueueRepository;

    @Override
    public List<OrderQueue> getOrderQueueByShopId(Integer shopId) {
        List<OrderQueueEntity> orderQueueEntities = this.orderQueueRepository.findOrderQueueEntitiesByShopId(shopId);
        if(!CollectionUtils.isEmpty(orderQueueEntities)){
            return orderQueueEntities.stream().map(orderQueueEntity -> {
                return  OrderQueue.builder().orderInQueue(orderQueueEntity.getOrderInQueue())
                        .id(orderQueueEntity.getId())
                        .queueIndex(orderQueueEntity.getQueueIndex())
                        .queueSize(orderQueueEntity.getQueueSize())
                        .shopId(orderQueueEntity.getShopId()).build();
            }).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public void saveOrderQueue(OrderQueue orderQueue) {
        this.orderQueueRepository.save(
                OrderQueueEntity.builder()
                        .id(orderQueue.getId())
                        .orderInQueue(orderQueue.getOrderInQueue())
                        .queueIndex(orderQueue.getQueueIndex())
                        .queueSize(orderQueue.getQueueSize())
                        .shopId(orderQueue.getShopId())
                        .build()
        );
    }
}
