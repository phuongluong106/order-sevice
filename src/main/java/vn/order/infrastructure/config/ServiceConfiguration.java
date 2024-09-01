package vn.order.infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.order.domain.service.OrderService;
import vn.order.domain.service.OrderServiceImpl;
import vn.order.infrastructure.adapter.OrderAdapter;
import vn.order.infrastructure.adapter.OrderEventsAdapter;
import vn.order.infrastructure.adapter.OrderQueueAdapter;

@Configuration
public class ServiceConfiguration {
    @Autowired
    OrderAdapter orderAdapter;
    @Autowired
    OrderEventsAdapter orderEventsAdapter;
    @Autowired
    OrderQueueAdapter orderQueueAdapter;
    @Bean("OrderService")
    OrderService OrderService(){
        return new OrderServiceImpl(orderAdapter, orderEventsAdapter, orderQueueAdapter);
    }
}
