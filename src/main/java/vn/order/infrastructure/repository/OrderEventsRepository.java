package vn.order.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.order.infrastructure.entities.OrderEventsEntity;

@Repository
public interface OrderEventsRepository extends JpaRepository<OrderEventsEntity, Integer> {
}
