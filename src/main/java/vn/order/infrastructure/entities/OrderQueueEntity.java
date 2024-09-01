package vn.order.infrastructure.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "ORDER_QUEUE")
public class OrderQueueEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "SHOP_ID")
    private Integer shopId;
    @Column(name = "QUEUE_SIZE")
    private Integer queueSize;
    @Column(name = "QUEUE_INDEX")
    private Integer queueIndex;
    @Column(name = "ORDER_IN_QUEUE")
    private Integer orderInQueue;
}
