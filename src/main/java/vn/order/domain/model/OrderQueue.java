package vn.order.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderQueue {
    private Integer id;
    private Integer shopId;
    private Integer queueSize;
    private Integer queueIndex;
    private Integer orderInQueue;
}
