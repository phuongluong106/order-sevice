package vn.order.infrastructure.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import vn.order.infrastructure.entities.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public OrderEntity findOrderById(Integer id);
}
