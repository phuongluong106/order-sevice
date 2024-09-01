package vn.order.domain.model;

import lombok.*;
import vn.order.domain.enums.OrderStatusEnum;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer shopId;
    private List<Integer> menuIds;
    private Integer customerId;
    private Integer id;
    private OrderStatusEnum status;
}
